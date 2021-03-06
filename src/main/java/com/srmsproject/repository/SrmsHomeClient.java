package com.srmsproject.repository;

import com.srmsproject.model.Group;
import com.srmsproject.model.Student;
import com.srmsproject.model.User;
import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SrmsHomeClient {
    public List<Student> getAllStudents() {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.exchange("http://localhost:8085/",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {})
                    .getBody();
        } catch (Exception e) {
            System.err.println("Exception in SrmsHomeClient getAllStudent(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

//    public Page<Group> getAllGroups(Pageable pageable) {
//        try {
//            RestTemplate rest = new RestTemplate();
//            return rest.exchange("http://localhost:8087/groupList",
//                    HttpMethod.GET, null, new ParameterizedTypeReference<Page<Group>>() {})
//                    .getBody();
//        } catch (Exception e) {
//            System.err.println("Exception in SrmsHomeClient getAllGroups(): " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }


    public Student getStudentById(long id) {
        try {
            RestTemplate rest = new RestTemplate();
            //Map<String, String> urlVariables = new HashMap<>();
            //urlVariables.put("username", username);
            return rest.getForObject("http://localhost:8085/studPage/{id}",
                    Student.class, id);
        } catch (Exception e) {
//            System.err.println("Exception in getStudentId(): " + e.getMessage());
//            e.printStackTrace();
        }
        return null;
    }
    public Student getStudentPageById(long id) {
        try {
            RestTemplate rest = new RestTemplate();
            //Map<String, String> urlVariables = new HashMap<>();
            //urlVariables.put("username", username);
            return rest.getForObject("http://localhost:8085/studPage/{id}",
                    Student.class, id);
        } catch (Exception e) {
            System.err.println("Exception in getStudentId(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Student saveStudent(Student student) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.postForObject("http://localhost:8085/thanks",
                    student, Student.class);
        } catch (Exception e) {
            System.err.println("Exception in SrmsHomeClient saveStudent(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public Group saveGroups(Group group) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.postForObject("http://localhost:8087/groupAdd",
                    group, Group.class);
        } catch (Exception e) {
            System.err.println("Exception in SrmsHomeClient saveGroups(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public Student deleteStudent(long id) {
        try {
            RestTemplate rest = new RestTemplate();
            Map<String, Long> urlVariables = new HashMap<>();
            urlVariables.put("id", id);
            return rest.getForObject("http://Crudpage:8085/studDel/{id}", Student.class, urlVariables);
        } catch (Exception e) {
//            System.err.println("Exception in SrmsHomeClient deleteStudent(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Student updateStudent(long id){
        try {
            RestTemplate rest = new RestTemplate();
            Map<String, Long> urlVariables = new HashMap<>();
            urlVariables.put("id", id);
            return rest.getForObject("http://localhost:8085/studUpdate/{id}", Student.class, urlVariables);
        } catch (Exception e) {
            System.err.println("Exception in SrmsHomeClient updateStudent(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User saveUser(User user) {
        try {
            RestTemplate rest = new RestTemplate();
            return rest.postForObject("http://localhost:8085/Student/user/register",
                    user, User.class);
        } catch (Exception e) {
            System.err.println("Exception in SrmsHomeClient saveUser: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
//    public User delUser(User user){
//        try{
//            RestTemplate rest = new RestTemplate();
//            return rest.postForObject("http://")
//        }
//    }

    public User getUserByUsername(String username)  {
        try {
            RestTemplate rest = new RestTemplate();
            Map<String, String> urlVariables = new HashMap<>();
            urlVariables.put("username", username);
            return rest.getForObject("http://localhost:8085/Student/user/{username}",
                    User.class, urlVariables);
        } catch (Exception e) {
            System.err.println("Exception in SrmsHomeClient: getUserByUsername() " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Student> getAllOrdersBySorting(String orderBy, String direction, String page, String size) {
        try {
            RestTemplate rest = new RestTemplate();
            String url = "http://localhost:8085/studlist?orderBy=" + orderBy + "&direction=" + direction;


            return rest.exchange(url + "&page=" + page + "&size=" + size,
//            return rest.exchange("http://localhost:8081/teachers/ps?orderBy=name&direction=DESC&page=0&size=5",
//            return rest.exchange("http://localhost:8081/teachers",
                    HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Student>>() {
                    })
                    .getBody();
        } catch (Exception e) {
            System.err.println("Exception in TacoCloudClient: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }




}
