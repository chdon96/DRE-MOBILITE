package com.example.chadi.Service;

import com.example.chadi.Email.EmailService;
import com.example.chadi.Entity.User;
import com.example.chadi.Registration.Token.ConfirmationToken;
import com.example.chadi.Registration.Token.ConfirmationTokenRepository;
import com.example.chadi.Registration.Token.ConfirmationTokenService;
import com.example.chadi.Repository.UserRep;
import com.example.chadi.token.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private  final static String USER_NOT_FOUND_MSG="User with email %s not found";
    private  final UserRep userRep;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRep.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }



    //*********************************************
   private final ConfirmationTokenService conTokSer; //CONFIRMATIONSERVICE *******************
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;




    //cheyraj3elna token*****************************************************************************************************************************************************************************************************************

    public String SendPassToken( String email){
        String token = UUID.randomUUID().toString();

        User user = userRep.findByEmail(email).get();

        ConfirmationToken updatedToken  = confirmationTokenRepository.findById(user.getIdUser()).get();//to get it by same id
///*********************************UPDATE*********************************
        updatedToken.setToken(token);
updatedToken.setCreatedAt(LocalDateTime.now());
updatedToken.setExpiredAt(LocalDateTime.now().plusMinutes(15));
//*************************************************************************

        emailService.sendEmailConfirm(email,"Your ID is "+user.getIdUser()+"\nHere is Your Token \nCopy to Reset Your Pass\n\n\n"+token);

        return "My Pleasure To Do Work With You  (^_^)!!";
    }



   public String ResetPass(Integer idUser,String token,String neoPass ){
     User user = userRep.findById(idUser).get();
     if (confirmationTokenRepository.findByToken(token)==null){
         throw new IllegalStateException("You have sent the wrong token!!!! (T-T)");
     }
       String encodedPassword =  bCryptPasswordEncoder
               .encode(neoPass);
       user.setPassword(encodedPassword);
       user.setEnabled(true);
       userRep.save(user);
       return "Your password has been reseted(^_^)";
   }
//***********************************SIGNUP
    /*public String SignUpUser(User user){
        boolean userExist = userRep
                .findByEmail(user.getEmail())
                .isPresent();

        //to check if exist*************************
        if (userExist){

            throw new IllegalStateException("email already exists!");

        }

        //check if pattern ..@..
//        String regex = "^(.*)@(.*)$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(user.getEmail());
//        if (!matcher.matches()){
//            throw new IllegalStateException("an email should be like this (example@mail.com)");
//        }
        //password exception
        if(user.getPassword().length()<8){
            throw new IllegalStateException("Your password is too short");
        }
        if(user.getPassword().length()>25){
            throw new IllegalStateException("that's too much for a password");
        }
        ///to encode password***********************
      String encodedPassword =  bCryptPasswordEncoder
                                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        //name control
        if (user.getFirstName().length()<2||user.getLastName().length()<2){
            throw new IllegalStateException("too short to be a name ,Insert a name please");
        }
        if(user.getRole()==null){
            user.setRole(Role.Etudiant);
        }

        ///generation token******************************************************************************
String token = UUID.randomUUID().toString();
        ///****************
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, //token
                LocalDateTime.now(), //createdAt
                LocalDateTime.now().plusMinutes(15), //expires
                user //yawzer
        );//creation token avec compt de user

        //save token
        userRep.save(user);
conTokSer.saveConfirmationToken(
        confirmationToken
);
//////////sendy conf tawkin******************************
        return token;
    }*/
//end tarji3 etawkin ta3 wodhni***********************************************************************************************************************************************************************
///////To enable User
    public int enableAppUser(String email) {
        return userRep.enableAppUser(email);
    }
    public int enableUser(String email) {
        return userRep.enableAppUser(email);
    }

/////****************MODIFY INFO

public User isCurrent(){
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            User user=(User) principal;
            return user;
        }
        return null;
}

    public boolean isCurrentUser(int id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            return user.getIdUser()==id;
        }
        return false;
    }




    public String signUpUser(User user) {
        boolean userExists = userRep
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRep.save(user);
        String token=UUID.randomUUID().toString();
        ConfirmationToken confirmationtoken= new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationtoken);
        return token;
        //TODO:SEND EMAIL
    }
}
