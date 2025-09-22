package edu.innotech;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.innotech.model.StudentModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RestTest {

    private static final String BASE_URL = "http://127.0.0.1:8080";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testCase1() {
        long id = createStudent("qwer", List.of(4, 5));
        StudentModel response = getStudent(id);
        Assertions.assertEquals(response.getName(), "qwer");
    }

    @Test
    public void testCase2() {
        // Создаем и удаляем чтобы быть уверенными что такого id в сервисе нет
        long id = createStudent("qwer", List.of());
        deleteStudent(id);
        RestAssured.given()
                .baseUri(BASE_URL + "/student/" + id)
                .given()
                .get()
                .then()
                .statusCode(404);
    }

    @Test
    public void testCase3() {
        long id = Long.parseLong(
                RestAssured.given()
                        .baseUri(BASE_URL + "/student")
                        .contentType(ContentType.JSON)
                        .body(new StudentModel("Иван", List.of()))
                        .post()
                        .then()
                        .statusCode(201)
                        .extract().body().asString()
        );
        StudentModel response = getStudent(id);
        Assertions.assertNotNull(response.getName());
    }

    @Test
    public void testCase4() {
        long id = createStudent("Петр", List.of());
        StudentModel student = getStudent(id);
        student.setName("Петр 1");
        updateOrCreateStudent(student);
        Assertions.assertEquals(getStudent(id), student);
    }

    @Test
    public void testCase5() {
        Long id = Long.parseLong(
                RestAssured.given()
                        .baseUri(BASE_URL + "/student")
                        .contentType(ContentType.JSON)
                        .body(new StudentModel("qwer", List.of()))
                        .post()
                        .then()
                        .extract().body().asString()
        );
        Assertions.assertNotNull(id);
    }

    @Test
    public void testCase6() {
        RestAssured.given()
                .baseUri(BASE_URL + "/student")
                .contentType(ContentType.JSON)
                .body(new StudentModel(null, List.of()))
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    public void testCase7() {
        long id = createStudent("asdf", List.of());
        RestAssured.given()
                .baseUri(BASE_URL + "/student/" + id)
                .contentType(ContentType.JSON)
                .delete()
                .then()
                .statusCode(200);
    }

    @Test
    public void testCase8() {
        // Создаем и удаляем чтобы быть уверенными что такого id в сервисе нет
        long id = createStudent("asdf", List.of());
        deleteStudent(id);
        RestAssured.given()
                .baseUri(BASE_URL + "/student/" + id)
                .contentType(ContentType.JSON)
                .delete()
                .then()
                .statusCode(404);
    }

    @Test
    public void testCase9() {
        deleteAllWithRanks();
        String response = RestAssured.given()
                    .baseUri(BASE_URL + "/topStudent")
                    .given()
                    .get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    public void testCase10() {
        deleteAllWithRanks();
        createStudent("qwer", List.of());
        String response = RestAssured.given()
                    .baseUri(BASE_URL + "/topStudent")
                    .given()
                    .get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();
        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    public void testCase11() {
        deleteAllWithRanks();
        createStudent("qwer", List.of(4, 4, 4, 3));
        createStudent("asdf", List.of(4, 5, 3));
        StudentModel[] students = RestAssured.given()
                    .baseUri(BASE_URL + "/topStudent")
                    .given()
                    .get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(StudentModel[].class);
        Assertions.assertEquals(students.length, 1);
        Assertions.assertEquals(students[0].getName(), "asdf");
    }

    @Test
    public void testCase12() {
        deleteAllWithRanks();
        createStudent("zxcv", List.of(3, 4, 5));
        createStudent("qwer", List.of(4, 4, 4, 3));
        createStudent("asdf", List.of(4, 5, 3));
        StudentModel[] students = RestAssured.given()
                    .baseUri(BASE_URL + "/topStudent")
                    .given()
                    .get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(StudentModel[].class);
        Assertions.assertEquals(students.length, 2);
        Assertions.assertEquals(students[0].getMarks().size(), students[1].getMarks().size());
    }

    private StudentModel getStudent(long id) {
        return RestAssured.given()
                .baseUri(BASE_URL + "/student/" + id)
                .given()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(StudentModel.class);
    }

    private long createStudent(String name, List<Integer> marks) {
        return Long.parseLong(
                RestAssured.given()
                        .baseUri(BASE_URL + "/student")
                        .contentType(ContentType.JSON)
                        .body(new StudentModel(name, marks))
                        .post()
                        .then()
                        .extract().body().asString()
        );
    }

    private void updateOrCreateStudent(StudentModel studentModel) {
        RestAssured.given()
                .baseUri(BASE_URL + "/student")
                .contentType(ContentType.JSON)
                .body(studentModel)
                .post()
                .then()
                .statusCode(201);
    }

    private void deleteStudent(Long id) {
        RestAssured.given()
                .baseUri(BASE_URL + "/student/" + id)
                .contentType(ContentType.JSON)
                .delete()
                .then()
                .statusCode(200);
    }

    @SneakyThrows
    private void deleteAllWithRanks() {
        StudentModel[] list;
        do {
            String responseString = RestAssured.given()
                    .baseUri(BASE_URL + "/topStudent")
                    .given()
                    .get()
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .body().asString();
            if (responseString.isBlank()) {
                return;
            }
            list = OBJECT_MAPPER.readValue(responseString, StudentModel[].class);
            for (StudentModel studentModel: list) {
                deleteStudent(studentModel.getId());
            }
        } while (list.length > 0);
    }
}
