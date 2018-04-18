package com.codecool.web.service;

import com.codecool.web.model.Assignment;

import java.util.*;

public class SubmittedAssignmentsDaoImpl implements SubmittedAssignmentsDao {
    private final Map<String,List<Assignment>> submittedAssignments = new HashMap<>();
    @Override
    public List<Assignment> getAssignmentsByEmail(String email) {
        return submittedAssignments.get(email);
    }

    /*@Override
    public Assignment getAssignmentForEmailByTitle(String email, String assignmentTitle) {
        Iterator it = submittedAssignments.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove();
            return pair.getValue();
        }
        return null;
    }*/

    @Override
    public void addToSubmittedAssignments(String email, Assignment assignment) {
        submittedAssignments.computeIfAbsent(email, k -> new ArrayList<>());
        submittedAssignments.get(email).add(assignment);

    }

    @Override
    public boolean isSubmitted(String email, String assignmentTitle) {
        if(getAssignmentsByEmail(email) == null){
            return false;
        }
        for (Assignment assignment:getAssignmentsByEmail(email)) {
            if(assignment.getTitle().equals(assignmentTitle)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Assignment getAssigmentForUser(String email, String assignmentTitle) {
        Iterator<Map.Entry<String, List<Assignment>>> it = submittedAssignments.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<Assignment>> pair = it.next();
            for (Assignment assignment: pair.getValue()) {
                if(assignment.getTitle().equals(assignmentTitle)){
                    return assignment;
                }
            }
        }
        return null;
    }
}
