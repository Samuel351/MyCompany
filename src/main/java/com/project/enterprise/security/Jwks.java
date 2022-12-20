/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.enterprise.security;

import com.nimbusds.jose.jwk.RSAKey;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 *
 * @author sscos
 */
public class Jwks {
    
    	public static RSAKey generateRsa() {
                // Chama a função de gerar a chaves e gera uma chave RSA
                
		KeyPair keyPair = KeyGenerator.generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		return new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
                                .keyID(UUID.randomUUID().toString())
				.build();
	}
    
}
