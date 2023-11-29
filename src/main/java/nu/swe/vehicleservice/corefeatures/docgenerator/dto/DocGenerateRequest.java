package nu.swe.vehicleservice.corefeatures.docgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a request for document generation.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocGenerateRequest {

    /** The template file name for the document generation. */
    private String template;

    /** The data object to be included in the document. */
    private Object data;

}