package org.elbialy.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.elbialy.dto.ProductDto;
import org.elbialy.model.Product;
import org.elbialy.service.ProductService;



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

    @PUT
    @Path("/update-product/{product-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("product-id")long id,
                                  ProductDto productDto){
       return Response.ok().entity(productService.update(id,productDto)).build();
    }

    @PATCH
    @Path("/update-product/{product-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProductWithPatch(@PathParam("product-id")long id
            ,ProductDto partialProduct){
        return Response.ok().entity(productService.updateWithPatch(id,partialProduct)).build();
    }

    @DELETE
    @Path("/delete/{product-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProduct(@PathParam("product-id")long id){
        return Response.ok().entity(productService.delete(id)).build();
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByCategory(@QueryParam("filter")String filter,
                                         @DefaultValue("0")@QueryParam("page")int page,
                                         @DefaultValue("10")@QueryParam("size")int size){
        return Response.ok().entity(productService.getByCategory(filter,page,size)).build();
    }
}
