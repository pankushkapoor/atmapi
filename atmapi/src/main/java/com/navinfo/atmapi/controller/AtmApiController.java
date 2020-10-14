package com.navinfo.atmapi.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.navinfo.atmapi.exceptions.InsufficientBalanceException;
import com.navinfo.atmapi.model.Account;
import com.navinfo.atmapi.repository.AccountRepository;
import com.navinfo.atmapi.service.AccountService;
import com.navinfo.atmapi.service.AuthenticationService;

@Controller
@RequestMapping("/atm")
public class AtmApiController {
	
//	@Autowired
//	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
    private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/getotp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String authorize(@RequestBody Account account) throws AccountNotFoundException {
        return authenticationService.generateOTP(account);
    }
	
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
	
	@RequestMapping("/withdraw/{otp}/{accountnumber}/{amount}")
    public @ResponseBody String withdraw(@PathVariable(value = "otp") String otp, 
    			@PathVariable(value = "accountnumber") long accountNumber, 
    			@PathVariable(value = "amount") int amount) {
        try {
            if (authenticationService.verifyOTP(accountNumber, otp)) {
                if (accountService.withdraw(accountNumber, amount)) {
                    return "Success";
                } else {
                    return "Insufficient Balance";
                }
            } else {
                return "Invalid OTP";
            }
        }
        catch (InsufficientBalanceException e) {
            e.printStackTrace();
            return "Insufficient Balance";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error occured. Please try again later.";
        }
    }
	
	@RequestMapping("/checkbalance/{otp}/{accountnumber}")
    public @ResponseBody double checkBalance(@PathVariable(value = "otp") String otp, 
    			@PathVariable(value = "accountnumber") long accountNumber) {
        try {
            if (authenticationService.verifyOTP(accountNumber, otp)) {
                return accountService.checkBalance(accountNumber);
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @RequestMapping("/deposit/{otp}/{accountnumber}/{amount}")
    public @ResponseBody String deposit(@PathVariable(value = "otp") String otp, 
    			@PathVariable(value = "accountnumber") long accountNumber, @PathVariable(value = "amount") int amount) {
        try {
            if (authenticationService.verifyOTP(accountNumber, otp)) {
                if (accountService.deposit(accountNumber, amount)) {
                    return "Success";
                } else {
                    return "Error occured. Please try again.";
                }
            } else {
                return "Invalid OTP";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occured. Please try again";
        }
    }
}