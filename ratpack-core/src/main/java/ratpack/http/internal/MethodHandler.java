/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ratpack.http.internal;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.HttpMethod;
import ratpack.http.Response;

public class MethodHandler implements Handler {

  private final String method;

  public static final Handler GET = new MethodHandler("GET");
  public static final Handler POST = new MethodHandler("POST");
  public static final Handler PUT = new MethodHandler("PUT");
  public static final Handler PATCH = new MethodHandler("PATCH");
  public static final Handler DELETE = new MethodHandler("DELETE");

  public MethodHandler(String method) {
    this.method = method.toUpperCase();
  }

  public void handle(Context context) {
    HttpMethod requestMethod = context.getRequest().getMethod();
    if (requestMethod.name(method)) {
      context.next();
    } else if (requestMethod.isOptions()) {
      Response response = context.getResponse();
      response.getHeaders().add(HttpHeaderConstants.ALLOW, method);
      response.status(200).send();
    } else {
      context.clientError(405);
    }
  }
}
