package com.kalibu.pocBack.controller;

import com.kalibu.pocBack.model.FooModel;
import com.kalibu.pocBack.repository.FooRepository;
import com.kalibu.pocBack.vo.FooVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/foo")
@CrossOrigin(exposedHeaders = "Acess-Control-Allow-Origin")
@Tag(name = "foo", description = "Rest API for Employees V2")
@Log
public class FooController {

    @Autowired
    private FooRepository fooRepository;

    @Operation(
            operationId = "/all",
            summary = "Get all foo models",
            description = "Get all foo models",
            parameters = {
                    @Parameter(name = "order", description = "Order the search", in = ParameterIn.QUERY,
                            style = ParameterStyle.SIMPLE,
                            examples = {
                                    @ExampleObject(name = "Ascending Order", value = "A",
                                            summary = "Ascending"),
                                    @ExampleObject(name = "Descending Order", value = "D",
                                            summary = "Descending")
                            })
            },
            tags = {"foo"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FooModel.class)))
                    }),
                    @ApiResponse(responseCode = "400", description = "error", content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @GetMapping("/all")
    public List<FooModel> getAll(@RequestParam(required = false) String order) {
        log.info("/all, order=" + order);

        if (StringUtils.isBlank(order)) {
            return fooRepository.findAll();
        } else {

            if ("A".equalsIgnoreCase(order)) {
                return fooRepository.findAll(Sort.by(Sort.Direction.ASC, "fooProperty"));
            } else if ("D".equalsIgnoreCase(order)) {
                return fooRepository.findAll(Sort.by(Sort.Direction.DESC, "fooProperty"));
            } else {
                throw new IllegalArgumentException("Invalid order, please use only 'A' for ASC or 'D' for DESC");
            }

        }

    }

    @Operation(
            operationId = "/create",
            summary = "Create new foo",
            description = "Create new foo",
            tags = {"foo"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FooModel.class)))
                    })
            }
    )
    @PostMapping("/create")
    public ResponseEntity<FooModel> create(@RequestBody FooVO vo) {
        log.info("/create, vo=" + vo);

        final FooModel model = fooRepository.save(new FooModel(vo.fooProperty()));

        return ResponseEntity.ok(model);
    }

}