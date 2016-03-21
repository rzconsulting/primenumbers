package primenumbers;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/primenumberservice")
public class PrimeNumberService {
	
	@Path("/filterbysieve/{l}")
	@GET
	@Produces("application/json")
	public Response eratosthenesSieve(@PathParam("l")Integer limit) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		StringBuilder builder = new StringBuilder();
		Set<Integer> pms = GeneratePrimes.generatePrimes(AlgoContainer.runEratosthenesSieve, limit);
	   	for (int prime : pms)
	   		builder.append(prime + " ");
		
	   	jsonObject.put("Prime numbers: ", builder.toString());
		String result = "@Produces(\"application/json\") Output: \n\nThere are " + pms.size() + " prime numbers less than: " + limit + "\n\n" + jsonObject;
		return Response.status(200).entity(result).build();
	}
	
	@Path("/naivesearch/{l}")
	@GET
	@Produces("application/xml")
	public String heuresticSearch(@PathParam("l")Integer limit) {
		StringBuilder builder = new StringBuilder();
		Set<Integer> pms = GeneratePrimes.generatePrimes(AlgoContainer.runHeuresticSearch, limit);
	   	for (int prime : pms)
	   		builder.append(prime + " ");
		
		String result = "@Produces(\"application/xml\") Output: \n\nThere are " + pms.size() + " prime numbers less than: \n\n" + limit;
		String primes = builder.toString();
		return "<primenumberservice>" + "<summary>" + result + "</summary>" + "<primes>" + primes + "</primes>" + "</primenumberservice>";
	}
}