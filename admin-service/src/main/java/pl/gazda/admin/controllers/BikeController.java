package pl.gazda.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.gazda.admin.services.CloudinaryService;
import pl.shop.bike.models.model.enums.BikeType;
import pl.shop.bike.models.model.request.SaveBikeRequest;
import pl.shop.bike.models.model.request.bike.SaveBikeResponse;
import pl.shop.bike.models.model.response.DeleteResponse;
import pl.shop.commons.clients.UpdateServiceClient;
import pl.shop.commons.errors.ErrorsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/adm/bike")
public class BikeController {

    @Autowired
    UpdateServiceClient updateServiceClient;

    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<?> saveBike(HttpServletRequest servletRequest,
                                                     @Valid @ModelAttribute SaveBikeRequest request,
                                                     BindingResult result) throws ServletException, IOException {
        if(result.hasErrors()){
           return ErrorsUtil.createErrorResponse(result, request.getItemType());
        }

        List<Part> partList = servletRequest.getParts().stream()
                .filter(item -> item.getName().startsWith("image"))
                .collect(Collectors.toList());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(URI.create("http://localhost:8765/api/update/bike"));

        MultipartEntityBuilder mulitpartEntity = MultipartEntityBuilder.create();
        mulitpartEntity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        partList.forEach(part -> {
            try {
                mulitpartEntity.addPart(part.getName(), new InputStreamBody(part.getInputStream(), part.getName()));
            } catch (IOException exception) {
                log.error("Error message: " + exception);
            }
        });

        String requestJSON = new ObjectMapper().writeValueAsString(request);

        mulitpartEntity.addPart("SaveBikeJSON", new InputStreamBody(IOUtils.toInputStream(requestJSON), "SaveBikeJSON"));
        post.setEntity(mulitpartEntity.build());

        CloseableHttpResponse response = httpClient.execute(post);
        return null;
    }

    @DeleteMapping
    ResponseEntity<DeleteResponse> deleteBike(@RequestParam(name = "id") Long bikesId,
                                              @RequestParam(name = "type") BikeType type) {
        return updateServiceClient.deleteBikes(bikesId, type);
    }

    @PostMapping("/save")
    public ResponseEntity<SaveBikeResponse> updateBike(@RequestBody SaveBikeRequest request) {
        return updateServiceClient.updateBike(request);
    }
}
