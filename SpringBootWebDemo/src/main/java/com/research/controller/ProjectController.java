package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Project;
import com.research.model.Pagination;
import com.research.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertProject(@RequestBody Project project) {

        Preconditions.checkNotNull(project != null, "Project is null");

        int count = projectService.insertProject(project);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(project);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteProjectById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Project id is illegal");

        int count = projectService.deleteProjectById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateProjectById(@RequestBody Project project) {
        Preconditions.checkNotNull(project != null && project.getId() != 0, "Project id is illegal");

        int count = projectService.updateProjectById(project);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(project);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Project id is illegal");


        Project project = projectService.getProjectById(id);

        return ResponseEntity.ok(project);
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjects(Pagination pagination) {
        Preconditions.checkNotNull(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkNotNull(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Project> projects = projectService.getProjects(pagination);

        return ResponseEntity.ok(projects);
    }
}
