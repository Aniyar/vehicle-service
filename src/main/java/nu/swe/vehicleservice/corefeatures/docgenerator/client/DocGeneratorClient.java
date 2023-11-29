package nu.swe.vehicleservice.corefeatures.docgenerator.client;

import nu.swe.vehicleservice.corefeatures.docgenerator.dto.DocGenerateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "doc-generator-client",
        url = "${microservices.doc-generator.url}/generate")
public interface DocGeneratorClient {

    /**
     * Generates an Excel document based on the provided request.

     * @param request The request containing the necessary information to generate the document.
     * @return A byte array representing the generated document.
     */
    @PostMapping(value = "/raw", consumes = "application/json")
    byte[] generate(@RequestBody DocGenerateRequest request);

}
