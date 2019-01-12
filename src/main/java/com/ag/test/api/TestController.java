package com.ag.test.api;

import com.ag.test.api.model.BalanceTestResult;
import com.ag.test.api.model.ToDoItem;
import com.ag.test.api.model.ToDoItemAddRequest;
import com.ag.test.api.model.ToDoItemUpdateRequest;
import com.ag.test.service.TodoStub;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@Api(description = "test API")
@Validated
@RequestMapping("/test/1.0/")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/tasks/validateBrackets", method = RequestMethod.GET,headers="Accept=application/json")
    @ApiOperation(value = "Validate input string for balanced brackets.",
            response = BalanceTestResult.class,
            tags = "Tasks")
    @ApiResponses(value = {
            @ApiResponse(code = 4000, message = "Invalid input parameters."),
            @ApiResponse(code = 500, message = "Unknown system error") })
    public BalanceTestResult validateBrackets(@ApiParam("Any string to be checked for brackets") @NotNull @Size(min = 2, max = 100) @RequestParam("input") String input) {

        BalanceTestResult response = new BalanceTestResult();
        response.setInput(input);
        response.setBalanced(BracketCheck.isBalanced(input.trim()));
        return response;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST,headers="Accept=application/json")
    @ApiOperation(value = "Create a todo item.",
            response = ToDoItem.class,
            tags = "Todo")
    @ApiResponses(value = {
            @ApiResponse(code = 4000, message = "Invalid input parameters."),
            @ApiResponse(code = 500, message = "Unknown system error") })
    public ToDoItem addItem(@Valid @RequestBody ToDoItemAddRequest toDoItemAddRequest) {
        return TodoStub.create(toDoItemAddRequest);
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET,headers="Accept=application/json")
    @ApiOperation(value = "Retrieve a specific item by id.",
            response = ToDoItem.class,
            tags = "Todo")
    @ApiResponses(value = {
            @ApiResponse(code = 4000, message = "Invalid input parameters."),
            @ApiResponse(code = 500, message = "Unknown system error") })
    public ToDoItem getItem(@PathVariable Long id) throws ItemNotFoundException {

        ToDoItem item = TodoStub.get(id);
        if (item == null) {
            throw new ItemNotFoundException("Requested item cannot be found.");
        }
        return item;
    }


    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PATCH,headers="Accept=application/json")
    @ApiOperation(value = "Modify an item.",
            response = ToDoItem.class,
            tags = "Todo")
    @ApiResponses(value = {
            @ApiResponse(code = 4000, message = "Invalid input parameters."),
            @ApiResponse(code = 500, message = "Unknown system error") })
    public ToDoItem modifyItem(@PathVariable Long id, @RequestBody ToDoItemUpdateRequest update) {

        return TodoStub.modify(id, update);
    }

}
