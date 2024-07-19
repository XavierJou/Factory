package formation.conceptdev.facto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.Role;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.exceptions.UtilisateurException;
import formation.conceptdev.facto.repositories.IDAOUtilisateur;

@Service
public class UtilisateurService implements UserDetailsService {
	
	@Autowired
	IDAOUtilisateur daoUtilisateur;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return daoUtilisateur.findByLogin(username).orElseThrow(() -> {
			throw new UsernameNotFoundException("Utilisateur inconnu");
		});
	}

	public void updatePassword(String login, String password) {
		Utilisateur utilisateur = daoUtilisateur.findByLogin(login).orElseThrow(() -> {
			throw new UsernameNotFoundException("Utilisateur inconnu");
		});
		utilisateur.setPassword(passwordEncoder.encode(password));
		daoUtilisateur.save(utilisateur);
	}
	
	public Utilisateur getById(Integer id) 
	{
		if(id==null) 
		{
			throw new RuntimeException("Impossible de find un utilisateur sans id");
		}
		Optional<Utilisateur> opt = daoUtilisateur.findById(id);
		if(opt.isPresent()) 
		{
			return opt.get();
		}
		return null;
	}
	
	public Utilisateur create(Utilisateur utilisateur) {
		if (utilisateur.getLogin() == null || utilisateur.getLogin().isBlank()
				|| daoUtilisateur.findByLogin(utilisateur.getLogin()).isPresent()) {
			throw new UtilisateurException("problème login");
		}

		if (utilisateur.getPassword() == null || utilisateur.getPassword().isBlank()) {
			throw new UtilisateurException("problème password");
		}

		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateur.setRole(Role.ROLE_USER);
		return daoUtilisateur.save(utilisateur);
	}
	
	public List<Utilisateur> getAll() 
	{
		return daoUtilisateur.findAll();
	}
	
	public Utilisateur insert(Utilisateur utilisateur) 
	{
		return daoUtilisateur.save(utilisateur);
	}
	
	public Utilisateur update(Utilisateur utilisateur) 
	{
		return daoUtilisateur.save(utilisateur);
	}
	
	public void deleteById(Integer id) 
	{
		daoUtilisateur.deleteById(id);
	}
	
	public void delete(Utilisateur utilisateur) 
	{
		deleteById(utilisateur.getId());
	}

}
