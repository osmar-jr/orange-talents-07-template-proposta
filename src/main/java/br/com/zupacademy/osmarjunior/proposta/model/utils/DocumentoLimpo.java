package br.com.zupacademy.osmarjunior.proposta.model.utils;

import br.com.zupacademy.osmarjunior.proposta.annotations.CpfOrCnpj;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class DocumentoLimpo {

    @NotBlank
    @CpfOrCnpj
    private String documento;

    public DocumentoLimpo(@NotBlank String documento) {
        this.documento = documento;
    }

    public String hash() {
        String documentoHash = DigestUtils.sha256Hex(this.documento);
        return documentoHash.toLowerCase();
    }
}
