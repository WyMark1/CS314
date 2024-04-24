package com.tco.requests;
import com.tco.requests.FindRequest;
import com.tco.misc.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestFindRequest {

    @Test
    @DisplayName("hca1197: Initial base test for FindRequest.")
    public void testBuildResponse() throws BadRequestException{
        String match = "testMatch";
        List<String> type = Arrays.asList("type1", "type2");
        List<String> where = Arrays.asList("where1", "where2");
        Integer limit = 10;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        try{
            findRequest.buildResponse();
        }
        catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
        }
        //checking each variable is set
        assertEquals("find", findRequest.getRequestType());
        assertEquals(match, findRequest.getMatch());
        assertEquals(type, findRequest.getType());
        assertEquals(where, findRequest.getWhere());
        assertEquals(limit, findRequest.getLimit());
    }
    @Test
    @DisplayName("josh1302: Test null type is being passed in.")
    public void testNullType() throws BadRequestException{
        boolean thrown = false;
        String match = "a";
        List<String> type = null;
        List<String> where = Arrays.asList("where1", "where2");
        Integer limit = 3;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        try{
            findRequest.buildResponse();
        }
        catch (Exception e) {
                thrown = true;
        }
        assertEquals(thrown, false);
    }
    @Test
    @DisplayName("josh1302: Test null where is being passed in.")
    public void testNullWhere() throws BadRequestException{
        boolean thrown = false;
        String match = "a";
        List<String> type = Arrays.asList("where1", "where2");
        List<String> where = null;
        Integer limit = 3;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        try{
            findRequest.buildResponse();
        }
        catch (Exception e) {
                thrown = true;
        }
        assertEquals(thrown, false);
    }
    @Test
    @DisplayName("josh1302: Test specified limit is being passed in.")
    public void testLimit() throws BadRequestException{
        String match = "";
        List<String> type = Arrays.asList("airport");
        List<String> where = Arrays.asList("United States");
        Integer limit = 3;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        try{
            findRequest.buildResponse();
        }
        catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(14921, findRequest.getFound());
    }
    @Test
    @DisplayName("josh1302: Test no type.")
    public void testNotype() throws BadRequestException{
        String match = "a";
        List<String> type = null;
        List<String> where = Arrays.asList("United States", "China");
        Integer limit = 3;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        try{
            findRequest.buildResponse();
        }
        catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(22027, findRequest.getFound());
    }

    @Test
    @DisplayName("hca1197: Test found returns total number found and not just limit.")
    public void testFoundFix() throws BadRequestException{
        String match = "she";
        List<String> type = null;
        List<String> where = null;
        Integer limit = 5;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        try{
            findRequest.buildResponse();
        }
        catch (Exception e) {
                fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(findRequest.getFound(), 454);
    }
}