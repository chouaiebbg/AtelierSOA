package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezvous")
public class RendezVousRessources {
    static RendezVousBusiness help = new RendezVousBusiness();

    // GET all RendezVous
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getListeRendezVous())
                .build();
    }

    // GET RendezVous by ID
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getRendezVousById(id))
                .build();
    }

    // POST to add a new RendezVous
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = help.addRendezVous(rendezVous);
        if (added) {
            return Response.status(201)
                    .entity("{\"message\":\"Rendez-vous ajouté avec succès\"}")
                    .build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Erreur lors de l'ajout du rendez-vous\"}")
                    .build();
        }
    }

    // PUT to update an existing RendezVous
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        if (rendezVous.getId() != id) {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"L'ID dans l'URL ne correspond pas à celui du rendez-vous\"}")
                    .build();
        }

        boolean updated = help.updateRendezVous(id,rendezVous);
        if (updated) {
            return Response.status(200)
                    .entity("{\"message\":\"Rendez-vous mis à jour avec succès\"}")
                    .build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Erreur lors de la mise à jour du rendez-vous\"}")
                    .build();
        }
    }

    // DELETE a RendezVous by ID
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = help.deleteRendezVous(id);
        if (deleted) {
            return Response.status(200)
                    .entity("{\"message\":\"Rendez-vous supprimé avec succès\"}")
                    .build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Erreur lors de la suppression du rendez-vous\"}")
                    .build();
        }
    }
}
