/******************************************************************************
 * Copyright (C) 2019 by the ARA Contributors                                 *
 *                                                                            *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 * 	 http://www.apache.org/licenses/LICENSE-2.0                               *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 *                                                                            *
 ******************************************************************************/

package com.decathlon.ara.scenario.cucumber.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {

    private String id;
    private String name;
    private String uri;
    private String description;
    private String keyword;
    private Integer line;
    private List<Comment> comments = new ArrayList<>();
    private List<Element> elements = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public String getReportFileName() {
        return uri.replaceAll("[^\\d\\w]", "-") + ".html";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyword() {
        return keyword;
    }

    public Integer getLine() {
        return line;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }
}
