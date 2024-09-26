package com.fdmgroup.apiPack.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.apiPack.model.Account;
import com.fdmgroup.apiPack.model.Address;
import com.fdmgroup.apiPack.model.Company;
import com.fdmgroup.apiPack.model.Customer;
import com.fdmgroup.apiPack.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDeserializer extends JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        String customerType = node.get("customerType").asText();

        String name = node.get("name").asText();
        JsonNode addressNode = node.get("address");

        Address address = mapper.treeToValue(addressNode, Address.class);

        if ("PERSON".equalsIgnoreCase(customerType)) {
            return new Person(name, address);
        } else if ("COMPANY".equalsIgnoreCase(customerType)) {
            return new Company(name, address);
        }

        throw new IllegalArgumentException("Unknown customer type: " + customerType);
    }
}
