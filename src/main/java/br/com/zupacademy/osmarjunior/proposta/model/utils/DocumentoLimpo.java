package br.com.zupacademy.osmarjunior.proposta.model.utils;

import br.com.zupacademy.osmarjunior.proposta.annotations.CpfOrCnpj;
import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class DocumentoLimpo {

    @NotBlank
    @CpfOrCnpj
    private String documento;

    @NotBlank
    private String documentoHash;

    public DocumentoLimpo(@NotBlank String documento) {
        this.documento = documento;
        this.documentoHash = this.hash();
    }

    private static StandardPBEStringEncryptor getEncryptor(String textHash) {
        StandardPBEStringEncryptor encryptorz = new StandardPBEStringEncryptor();
        encryptorz.setPassword(textHash);
        encryptorz.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptorz.setIvGenerator(new RandomIvGenerator());

        return encryptorz;
    }

    private String hash() {
        String documentoHash = DigestUtils.sha256Hex(this.documento);
        return documentoHash.toLowerCase();
    }

    public String getHash() {
        return documentoHash;
    }

    public String encrypt() {
        return getEncryptor(this.documentoHash).encrypt(this.documento);
    }

    public static String decrypt(String textToDecode, String textHash) {
        return getEncryptor(textHash).decrypt(textToDecode);
    }

}
