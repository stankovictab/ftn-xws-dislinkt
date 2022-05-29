package dislinkt.agentservice.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

import dislinkt.agentclient.AgentDTO;
import dislinkt.agentservice.Entity.Agent;
import dislinkt.agentservice.Mapper.AgentMapper;
import dislinkt.agentservice.Repository.AgentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AgentService {

    private final AgentRepository agentRepository;

    private final AgentMapper agentMapper;

    public Agent setApiToken(String userId, String apiToken) {
        Agent agent = agentRepository.findById(userId).get();
        agent.setApiToken(apiToken);
        if (agentRepository.save(agent) != null) {
            System.out.println("Api token set");
            return agent;
        }
        System.out.println("Api token not set");
        return null;
    }

    public Agent getAgent(String agentId) {
        return agentRepository.findById(agentId).orElse(null);
    }

    public void setAdmin(String username) {
        Agent agent = agentRepository.findByUsername(username).get(0);
        agent.setRole("Admin");
        agentRepository.save(agent);
    }

    public Boolean setOwner(String agentId) {
        Agent agent = agentRepository.findById(agentId).get();

        if (agent != null && agent.getRole().equals("User")) {
            agent.setRole("Owner");
        }
        else {
            System.out.println("Agent not found or not a User");
            return null;
        }

        if (agentRepository.save(agent) != null) {
            System.out.println("Agent role changed to Owner");
            return true;
        } else {
            System.out.println("Agent role not changed to Owner");
            return false;
        }
    }

    public Boolean checkUsername(String username) {
        return agentRepository.findByUsername(username).isEmpty();
    }

    public Agent login(AgentDTO incomingAgent) {
        Agent agent = agentMapper.dtoToEntity(incomingAgent);
        ArrayList<Agent> agents = agentRepository.findByUsername(agent.getUsername());
        Agent allegedAgent = null;
        for (Agent agent2: agents) {
            if (agent2.getUsername().equals(agent.getUsername())) {
                allegedAgent = agent2;
                break;
            }
        }
        if (allegedAgent == null) {
            System.out.println("User not found.");
            System.out.println("Attempted username : '" + incomingAgent.getUsername() + "'.");
            return null;
        }

        agent.setPasswordSalt(allegedAgent.getPasswordSalt());
        SecretKeyFactory skf;

        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(agent.getPasswordInput().toCharArray(), agent.getPasswordSalt(), 65536, 256);
            byte[] passwordHash;

            try {
                passwordHash = skf.generateSecret(spec).getEncoded();
                
                if (Arrays.equals(passwordHash, allegedAgent.getPasswordHash())) {
                    System.out.println("User '" + agent.getUsername() + "' has successfully logged in.");
                    allegedAgent.setPasswordSalt(null);
                    allegedAgent.setPasswordHash(null);
                    return allegedAgent;
                }
                else {
                    System.out.println("Wrong password.");
                    System.out.println("Attempted username : '" + agent.getUsername() + "'.");
                    return null;
                }
            } catch (InvalidKeySpecException e) {
                System.out.println("Invalid key spec.");
                e.printStackTrace();
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm.");
            e.printStackTrace();
            return null;
        }
    }



    public Agent register(AgentDTO incomingAgent) {        
        Agent agent = agentMapper.dtoToEntity(incomingAgent);
        agent.setRole("User");

        // password handling
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        agent.setPasswordSalt(salt);

        SecretKeyFactory skf;

        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(agent.getPasswordInput().toCharArray(), agent.getPasswordSalt(), 65536, 256);
            byte[] passwordHash;

            try {
                passwordHash = skf.generateSecret(spec).getEncoded();
                agent.setPasswordHash(passwordHash);
                agent.setPasswordInput(null);

                ArrayList<Agent> agents = agentRepository.findByUsername(agent.getUsername());
                for (Agent a : agents) {
                    if (a.getUsername().equalsIgnoreCase(agent.getUsername())) {
                        System.out.println("Agent '" + agent.getUsername() + "' already exists.");
                        return null;
                    }
                }
                agent = agentRepository.save(agent);
                if (agent != null) {
                    System.out.println("Agent '" + agent.getUsername() + "' successfully registered.");
                    agent.setPasswordSalt(null);
                    agent.setPasswordHash(null);
                    return agent;
                }
                else {
                    System.out.println("Agent could not be registered.");
                    return null;
                }
            } catch (InvalidKeySpecException e) {
                System.out.println("Invalid key spec.");
                e.printStackTrace();
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm.");
            e.printStackTrace();
            return null;
        }         
    }
    
}
