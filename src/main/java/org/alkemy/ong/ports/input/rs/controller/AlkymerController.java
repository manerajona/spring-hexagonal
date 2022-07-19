package org.alkemy.ong.ports.input.rs.controller;

import lombok.RequiredArgsConstructor;
import org.alkemy.ong.config.exception.NotFoundException;
import org.alkemy.ong.core.model.Alkymer;
import org.alkemy.ong.core.model.AlkymerList;
import org.alkemy.ong.core.usecase.AlkymerService;
import org.alkemy.ong.ports.input.rs.api.AlkymersApi;
import org.alkemy.ong.ports.input.rs.api.ApiConstants;
import org.alkemy.ong.ports.input.rs.dto.AlkymerResponse;
import org.alkemy.ong.ports.input.rs.dto.AlkymerResponseList;
import org.alkemy.ong.ports.input.rs.dto.CreateAlkymerRequest;
import org.alkemy.ong.ports.input.rs.dto.UpdateAlkymerRequest;
import org.alkemy.ong.ports.input.rs.mapper.AlkymerControllerMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.alkemy.ong.ports.input.rs.api.ApiConstants.ALKYMERS_URI;

@RestController
@RequestMapping(ALKYMERS_URI)
@RequiredArgsConstructor
public class AlkymerController implements AlkymersApi {

    private final AlkymerService service;
    private final AlkymerControllerMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> createAlkymers(CreateAlkymerRequest createAlkymerRequest) {

        Alkymer alkymer = mapper.createAlkymerRequestToAlkymer(createAlkymerRequest);

        final long id = service.createEntity(alkymer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<AlkymerResponseList> getAlkymers(@RequestParam Optional<Integer> page,
                                                           @RequestParam Optional<Integer> size) {

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        AlkymerList list = service.getList(PageRequest.of(pageNumber, pageSize));

        AlkymerResponseList response;
        {
            response = new AlkymerResponseList();

            List<AlkymerResponse> content = mapper.alkymerListToAlkymerResponseList(list.getContent());
            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());
        }
        return ResponseEntity.ok().body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AlkymerResponse> getAlkymer(@PathVariable Long id) {
        Alkymer alkymer = service.getByIdIfExists(id).orElseThrow(() -> new NotFoundException(id));
        AlkymerResponse response = mapper.alkymerToAlkymerResponse(alkymer);
        return ResponseEntity.ok(response);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAlkeymer(@PathVariable Long id, UpdateAlkymerRequest updateAlkymerRequest) {
        Alkymer alkymer = mapper.updateAlkymerRequestToAlkymer(updateAlkymerRequest);
        service.updateEntityIfExists(id, alkymer).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlkeymer(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
