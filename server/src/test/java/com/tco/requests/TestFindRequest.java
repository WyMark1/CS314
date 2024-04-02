package com.tco.requests;
import com.tco.requests.FindRequest;
import com.tco.misc.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFindRequest {

    @Test
    @DisplayName("hca1197: Initial base test for FindRequest.")
    public void testBuildResponse() {
        String match = "testMatch";
        List<String> type = Arrays.asList("type1", "type2");
        List<String> where = Arrays.asList("where1", "where2");
        Integer limit = 10;
        FindRequest findRequest = new FindRequest(match, type, where, limit);
        findRequest.buildResponse();
        assertEquals("find", findRequest.getRequestType());
    }
}