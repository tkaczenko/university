package controller;

import city.City;
import country.Country;
import service.CountryService;
import service.CountryServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tkaczenko on 02.12.16.
 */
@WebServlet("/countries")
public class CountryController extends HttpServlet {
    private CountryService service;

    private boolean success = false;

    @Override
    public void init() throws ServletException {
        service = new CountryServiceImp();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Country country = parseCountry(req);
        if (country == null) {
            success = false;
            processResult(resp);
            return;
        }
        service.create(country);
        success = true;
        processResult(resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countryCode = req.getHeader("countrycode");
        if (countryCode == null) {
            List<Country> countries = service.findAll();
            if (countries == null) {
                success = false;
                processResult(resp);
            } else {
                success = true;
                processResult(resp);
                printListOfCountries(countries, resp);
            }
        } else {
            Country result = service.findOne(countryCode);
            validateResult(result, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countryCode = req.getHeader("countrycode");
        if (countryCode == null) {
            success = false;
            processResult(resp);
            return;
        }
        Country result = service.delete(countryCode);
        validateResult(result, resp);
    }

    private void validateResult(Country country, HttpServletResponse resp) {
        if (country == null) {
            success = false;
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            success = true;
            processResult(resp);
        }
    }

    private void printListOfCountries(List<Country> countries, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String header = "List of country with capitals:";
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + header + "</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>" + "CODE" + "</th>");
        out.println("<th>" + "COUNTRY" + "</th>");
        out.println("<th>" + "CAPITAL" + "</th>");
        countries.forEach(country -> {
            out.println("<tr>");
            out.println("<td>" + country.getCode() + "</td>");
            out.println("<td>" + country.getName() + "</td>");
            City capital = country.getCapital();
            out.println("<td>" + (capital != null ? capital.getName() : "null") + "</td>");
            out.println("</tr>");
        });
        out.println("</table>");
    }

    private Country parseCountry(HttpServletRequest req) {
        String sa = req.getHeader("surfacearea");
        return new Country.Builder(req.getHeader("code"), req.getHeader("name"), req.getHeader("continent"),
                req.getHeader("region"), sa != null ? Double.parseDouble(sa) : 0, req.getIntHeader("population"),
                req.getHeader("localname"), req.getHeader("governmentForm"), req.getHeader("code2"))
                .build();
    }

    private void processResult(HttpServletResponse resp) {
        if (success) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
