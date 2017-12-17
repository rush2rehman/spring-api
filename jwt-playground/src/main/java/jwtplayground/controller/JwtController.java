package jwtplayground.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.time.Instant;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

  @RequestMapping("/")
  public String jwttest()
  {
    return "I am a JWT sample app..";
  }

  @RequestMapping("/GenerateHardCodedToken")
  public String hcjwt()
  {
    String jws = Jwts.builder()
        .setIssuer("Stormpath")
        .setSubject("msilverman")
        .claim("name", "Micah Silverman")
        .claim("scope", "admins")
        // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
        .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
        // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
        .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
        .signWith(
            SignatureAlgorithm.HS256,
            TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
        )
        .compact();
    return jws;
  }


}
