<?php

class Custom_Customer_Model_UnsafeLoader
{
    protected $connection;

    public function __construct()
    {
        // Direct DB connection (bad practice)
        $this->connection = new PDO('mysql:host=localhost;dbname=magento', 'root', 'password');
    }

    public function getCustomerByEmail($email)
    {
        $query = "SELECT * FROM customer_entity WHERE email = '$email'";
        $result = $this->connection->query($query);

        return $result->fetch(PDO::FETCH_ASSOC);
    }

    public function getAllCustomers()
    {
        $query = "SELECT * FROM customer_entity";
        $result = $this->connection->query($query);

        return $result->fetchAll(PDO::FETCH_ASSOC);
    }
}

