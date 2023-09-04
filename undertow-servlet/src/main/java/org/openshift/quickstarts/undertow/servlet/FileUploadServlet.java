/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.openshift.quickstarts.undertow.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

/**
 * @author Stuart Douglas
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUploadServlet extends HttpServlet {

    public static final String MESSAGE = "message";

    private String message;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uploadPath = getServletContext().getRealPath("Teste");

        /* Receive file uploaded to the Servlet from the HTML5 form */
        System.out.println("doPost: " +uploadPath);
        try {
            System.out.println("doPost" + request.getParts() != null ? request.getParts().size() : -1);
        }catch (Exception e ){
            System.out.println("doPost: error: " + e.getMessage());
        }
        System.out.println("Request: " + request.getContentType());

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        for (Part part : request.getParts()) {
           part.write(fileName);
        }
        response.getWriter().print("The file uploaded sucessfully.");
    }
}
