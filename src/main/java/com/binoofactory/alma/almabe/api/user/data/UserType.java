package com.binoofactory.alma.almabe.api.user.data;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public enum UserType implements GrantedAuthority {
	NONE("비회원", 1), 
	외부사용자("외부사용자", 11), 
	내부사용자("내부사용자", 12), 
	채널관리자("채널관리자", 121), 
	슈퍼관리자("슈퍼관리자", 122);
    
	private final String description;
    
    private final int code;
	
	UserType(String description, int code) {
		this.description = description;
		this.code = code;
	}

    @Override
    public String getAuthority() {
        return description;
    }
    
    public Collection<GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(UserType type : UserType.values()) {
            if(type.code < this.code) {
                authorities.add(new SimpleGrantedAuthority(description));
            }
        }
        return authorities;
    }
}
