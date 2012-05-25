package org.ii.kucharyna.persistance.communication;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;

import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;
import com.google.mockwebserver.RecordedRequest;

public class MongoLabCommunicatorTest {

  private class MongoRequestStub extends MongoRequest {
    String url = "";

    MongoRequestStub(String url) {
      super("", "", null, "");
      this.url = url;
    }

    @Override
    HttpUriRequest getHttpRequest() throws URISyntaxException {
      return new HttpGet(this.url);
    }

    @Override
    protected String buildPath() {
      return "";
    }
  }

  @Test
  public void testSendReceive_validResponse() throws IOException,
      URISyntaxException, InterruptedException {
    MockWebServer server = new MockWebServer();
    server.enqueue(new MockResponse().setBody("test response"));
    server.play();

    final URL url = server.getUrl("/test?param=value");
    MongoRequest mr = new MongoRequestStub(url.toString());
    MongoLabCommunicator communicator = new MongoLabCommunicator();
    String response = communicator.sendReceive(mr);
    assertEquals("test response", response);

    assertEquals(1, server.getRequestCount());
    RecordedRequest rec = server.takeRequest();
    assertEquals("GET /test?param=value HTTP/1.1", rec.getRequestLine());
    server.shutdown();
  }

  @Test
  public void testSendReceive_retries() throws IOException, URISyntaxException,
      InterruptedException {
    MockWebServer server = new MockWebServer();
    server.enqueue(new MockResponse().setBody("test response")
        .setBytesPerSecond(0));
    server.enqueue(new MockResponse().setBody("test response2")
        .setBytesPerSecond(0));
    server.enqueue(new MockResponse().setBody("test response3"));
    server.play();

    final URL url = server.getUrl("/test?param=value");
    MongoRequest mr = new MongoRequestStub(url.toString());
    MongoLabCommunicator communicator = new MongoLabCommunicator();
    communicator.setSocketTimeout(100);
    String response = communicator.sendReceive(mr);
    assertEquals("test response3", response);

    assertEquals(3, server.getRequestCount());
    RecordedRequest rec = server.takeRequest();
    assertEquals("GET /test?param=value HTTP/1.1", rec.getRequestLine());
    server.shutdown();
  }

}
