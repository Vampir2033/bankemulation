package ru.dorogin.bankemulation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dorogin.bankemulation.entities.Operation;
import ru.dorogin.bankemulation.entities.OperationType;
import ru.dorogin.bankemulation.entities.User;
import ru.dorogin.bankemulation.repositories.OperationRepository;
import ru.dorogin.bankemulation.services.OperationService;
import ru.dorogin.bankemulation.services.UserService;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/operations")
public class OperationController {
    private UserService userService;
    private OperationService operationService;
    private OperationRepository operationRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOperationService(OperationService operationService) {
        this.operationService = operationService;
    }

    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @GetMapping("/history/{accountId}")
    @ResponseBody
    String getOperations(Principal principal,
                                  @PathVariable(name = "accountId") String accountId){
        User user = userService.findUserByUsername(principal.getName());
        try{
            StringBuilder result = new StringBuilder();
            for(Operation op : operationService.getAccountOperations(user, accountId))
                result.append(op.getAccountId())
                        .append("\t")
                        .append(op.getOperation())
                        .append((op.getOperation() != OperationType.CLOSE) ? "\t" + op.getMoney().toString() : "")
                        .append("<br>");
            return result.toString();
        } catch (Exception ex){
            return ex.getMessage();
        }
    }
}
