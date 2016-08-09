package org.jasig.cas.cg;

import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.util.CommonHelper;
import org.pac4j.http.credentials.TokenCredentials;
import org.pac4j.http.credentials.authenticator.TokenAuthenticator;
import org.pac4j.http.profile.HttpProfile;

/**
 * Created by ishara on 8/8/2016.
 */
public class CGAuthenticator implements TokenAuthenticator
{
    @Override public void validate( TokenCredentials credentials )
    {
        if( credentials == null )
        {
            throw new CredentialsException( "credentials must not be null" );
        }
        else if( CommonHelper.isBlank( credentials.getToken() ) )
        {
            throw new CredentialsException( "token must not be blank" );
        }
        else
        {
            String token = credentials.getToken();
            HttpProfile profile = new HttpProfile();
            profile.setId( token );
            credentials.setUserProfile( profile );
        }
    }
}
