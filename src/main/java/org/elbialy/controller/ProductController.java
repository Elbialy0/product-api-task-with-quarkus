package org.elbialy.controller;

import io.quarkus.security.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.elbialy.model.Product;
import org.elbialy.service.ProductService;

import javax.xml.namespace.QName;

@Path("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(@DefaultValue("0")@QueryParam("page") int page,
                                   @DefaultValue("10")@QueryParam("size") int size){
        return Response.ok()
                .entity(productService.getAll(page,size))
                .build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product){
        return Response.status(Response.Status.CREATED).entity(productService.add(product)).build();
    }

    @GET
    @Path("/{product-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("product-id")long id){
        return Response.ok().entity(productService.getById(id)).build();
    }
}
