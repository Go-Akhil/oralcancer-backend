package com.oralcancer.oralcancer.Service;

import com.oralcancer.oralcancer.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtilService {
    private String key=null;
    public String getSecreateKey(){
        key="dGhpcyBpcyBhIHNlY3JldCBrZXkgZm9yIGp3dCBhdXRoZW50aWNhdGlvbiBpbiBzcHJpbmcgYm9vdA==";
        return key;
    }


    public String generateToken(User user){
        Map<String ,Object> claims=new HashMap<>();

        return Jwts.builder()
                .subject(user.getUserName())
                .claims()
                .add(claims)
                .issuer("Akhil")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*10*1000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    public SecretKey generateKey(){

        byte[] x = Decoders.BASE64.decode(getSecreateKey());

        return Keys.hmacShaKeyFor(x);

    }


    public String extractUserName(String token) {

        return extractsClaims(token, Claims::getSubject);
    }

    private <T>T extractsClaims(String token, Function<Claims,T> claimResolver) {
        Claims claims=extractClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName=extractUserName(token);

        return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpriration(token).before(new Date());
    }

    private Date extractExpriration(String token) {
        return extractsClaims(token,Claims::getExpiration);
    }
}
