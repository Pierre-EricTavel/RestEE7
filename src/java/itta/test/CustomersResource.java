/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itta.test;

import itta.cours.data.Customer;

import itta.cours.ejbdata.CustomerFacadeLocal;
import java.util.List;

import javax.ejb.EJB;

import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/customer")
public class CustomersResource {

    @EJB(lookup = "java:global/RestPerrin/CustomerFacade")
    CustomerFacadeLocal customerFacade;
    
    @Context
    private UriInfo context;

    
    public CustomersResource() throws NamingException {

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getJson() {
        List<Customer> lc =customerFacade.findAll();
        return lc;
        
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(itta.cours.data.Customer customer) {
         customerFacade.create(customer);
        return Response.created(context.getAbsolutePath()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Customer customer) {
        customerFacade.edit(customer);
    }


    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id) {
        customerFacade.remove(new Customer(id));
    }
    
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerResource(@PathParam("id") int id) {
        return customerFacade.find(id);
    }
}
