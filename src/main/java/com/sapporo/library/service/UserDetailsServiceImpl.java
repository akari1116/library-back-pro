/**
 * 
 */
package com.sapporo.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sapporo.library.entity.Adomin;
import com.sapporo.library.repository.AdominRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	AdominRepository adominRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("ああああああああああああ" + email);	
		Adomin adomin = adominRepository.findByEmail(email);

        if(adomin == null){
            throw new UsernameNotFoundException(email);
        }

        System.out.println("email : " + email);
        
        

        return User.withUsername(email)
                .password(adomin.getPassword())
                .authorities("ROLE_USER") // ユーザの権限
                .build();
	}

}
