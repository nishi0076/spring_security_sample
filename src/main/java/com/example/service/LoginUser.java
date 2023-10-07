package com.example.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.entity.User;

public class LoginUser implements UserDetails {

	 // Userオブジェクト(Entityクラス)
	private final User user;
	
	
	// コンストラクタ、フィールドuserの代入に利用
	public LoginUser(User user) {
		this.user = user;
	}


	// userのgetter
	public User getUser() {
		return user;
	}

	
	// ユーザーの認証に使用されるパスワードを返却
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	
	
	// ユーザーの認証に使用されるユーザー名(今回はemali)を返却
	public String getUsername() {
		return this.user.getEmail();
	}
	
	
	// ユーザーに付与された権限を返却
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	// ロールカラムと照らし合わせて、認証ユーザーにロールを設定
    	if (this.user.getRole().equals("管理者")) {
    		return AuthorityUtils.createAuthorityList("ADMIN", "GENERAL");
    	}
    	
        return AuthorityUtils.createAuthorityList("GENERAL");
    }

    
    // アカウントの有効期限の状態を判定
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    
    // アカウントのロック状態を判定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    
    // 資格情報の有効期限の状態を判定
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    
    // 有効なユーザかを判定
    @Override
    public boolean isEnabled() {
        return true;
    }
}
