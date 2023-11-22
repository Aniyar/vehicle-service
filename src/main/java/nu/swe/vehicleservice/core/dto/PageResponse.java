package nu.swe.vehicleservice.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * A generic class representing a paginated response containing a list of items.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    
    @Schema(description = "List of content items for the current page")
    private List<T> content;

    @Schema(description = "Total number of pages available", example = "1")
    private int totalPages;

    @Schema(description = "Total number of items available across all pages", example = "1")
    private long totalElements;

    /**
     * Creates a PageResponse from a Page object.
     *
     * @param page {@link Page} object containing paginated data.
     * @return {@link PageResponse} containing the content, total pages, and total elements.
     */
    public static <T> PageResponse<T> fromPage(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
