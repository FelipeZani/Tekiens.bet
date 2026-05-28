package com.SurveyApp.userProfile.jwtTokens.services;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;

import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;


@Service
public class JwtTokenService {

    public PrivateKey loadPrivateKey() throws InvalidKeyException, SignatureException {
        try {
            Security.addProvider(new BouncyCastleProvider());

            Dotenv dot = Dotenv.load();
            String payload = dot.get("privateKey");

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
            keyGen.initialize(ecSpec);
            KeyPair keyPair = keyGen.generateKeyPair();

            ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
            
            byte [] signedPayload = signKey(payload, (PrivateKey) privateKey);
            System.out.println("Signature"+Base64.getEncoder().encodeToString(signedPayload));
            

            return privateKey;
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;

    }

    public Boolean verifySignedPayLoad(byte[] signedPayload,String payload ,PublicKey publicKey) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature verifier = Signature.getInstance("SHA256withECDSA");
            verifier.initVerify( publicKey);
            verifier.update(payload.getBytes());
            boolean verified = verifier.verify(signedPayload);
        return verified;
    }

    public byte[] signKey(String payload, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
          Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign( privateKey);
          
            signature.update(payload.getBytes());
            byte[] signedPayload = signature.sign();
            return signedPayload;
        
    }

}
