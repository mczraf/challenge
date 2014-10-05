package com.closenesscentrality.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.closenesscentrality.bean.Graph;
import com.closenesscentrality.bean.Node;
import com.closenesscentrality.bean.xml.RequestAddEdges;
import com.closenesscentrality.bean.xml.RequestCreateGraph;
import com.closenesscentrality.bean.xml.ResponseGraphCentrality;
import com.closenesscentrality.core.GraphUtils;

@Path("/graph")
public class GraphResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	/**
	 * Receives and parse the request for the creation of a graph.
	 * 
	 * @param jaxbContact
	 * @return Response
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response createGraph(JAXBElement<RequestCreateGraph> jaxbContact) {

		RequestCreateGraph requestCreateGraph = jaxbContact.getValue();
		
		return processCreateGraph(requestCreateGraph);
	}

	/**
	 * Process the request for the creation of a graph.
	 * 
	 * @param requestCreateGraph
	 * @return Response
	 */
	private Response processCreateGraph(RequestCreateGraph requestCreateGraph) {

		Graph.createGraph(requestCreateGraph.getMaxNodes());
		
		Response response = Response.ok().build();
		
		return response;
	}
	
	/**
	 * Receives and parse the request for the addition of edges.
	 * 
	 * @param jaxbContact
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response addEdges(JAXBElement<RequestAddEdges> jaxbContact) {
		
		RequestAddEdges requestAddEdges = jaxbContact.getValue();
		
		return processAddEdges(requestAddEdges);
	}

	/**
	 * Process the request for the addition of edges.
	 * 
	 * @param requestAddEdges
	 * @return Response
	 */
	private Response processAddEdges(RequestAddEdges requestAddEdges) {
		
		Response response;
		
		try {
		
			Graph graph = Graph.getGraph();
			
			if (graph != null) {
		        
		        graph.addEdges(requestAddEdges.getEdges());
		        
		        response = Response.ok().build();
		        
			} else {
				response = Response.serverError().build();
			}
		} catch (Exception e) {
			
			response = Response.serverError().build();
			e.printStackTrace();
		}
		
    	return response;
	}

	/**
	 * Receives and process the request for the closeness centrality computation.
	 * @return Response
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ResponseGraphCentrality getCentrality() {
		
		Graph graph = Graph.getGraph();
    	ResponseGraphCentrality responseGC = new ResponseGraphCentrality();
		
    	if (graph == null) {
			
    		responseGC.setStatus("There is no available graph. Please, create a new graph.");
		
    	} else  {
			
    		if (graph.getEdges() != 0) {
	    	
    			GraphUtils graphUtils = new GraphUtils(graph);
				
				List<Node> verticesSortedByCloseness = graphUtils.computeClosenessCentrality(graph);
				
		        responseGC.setNodes(verticesSortedByCloseness);
		    	responseGC.setStatus("Closeness graph centrality computed with success.");
		    	
			} else {
				responseGC.setStatus("There is no edge in the current graph. Please, add new edges.");
			}
		}
    	return responseGC;
	}
}
