package dataAccess;

import exception.DataAccessException;
import exception.DbConnectionException;
import model.*;
import tool.DAO;

import javax.jws.WebParam;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBIncocar implements DAO {
    //DB Gestion
    public void closeConnection(){
        SingletonConnection.closeConnection();
    }

    // Obtentions
    public String [] getAllLocalities() throws Exception {
        try {
            ArrayList<String> list = new ArrayList<>();
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select name from locality order by name asc");
            ResultSet data = statement.executeQuery();

            while (data.next()) {
                list.add(data.getString("name"));
            }
            return list.toArray(new String[list.size()]);
        } catch (DbConnectionException exception){
            throw new DataAccessException("Connexion à la source de stockage");
        } catch (SQLException exception) {
            throw new DataAccessException("Lecture du stockage");
        }
    }

    public Locality getLocality(String name) throws DataAccessException {
        try {
            ResultSet data;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from locality where name = ?");

            statement.setString(1, name);
            data = statement.executeQuery();
            if(data.next()){
                String zipCode = data.getString("zip_code");
                String country = data.getString("country");
                return new Locality(name,zipCode,country);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture localité" + e.getMessage());
        }
        return null;
    }

    public ArrayList<Supplier> getAllSuppliers() throws Exception {
        try {
            String tva;
            String name;
            String street;
            String streetNumber;
            String phone;
            Locality locality;
            ArrayList<Supplier> suppliers = new ArrayList<>();
            ResultSet data;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from supplier order by name");

            data = statement.executeQuery();
            while(data.next()) {
                tva = data.getString("tva_number");
                name = data.getString("name");
                street = data.getString("street");
                streetNumber = data.getString("street_number");
                phone = data.getString("phone_number");
                locality = getLocality(data.getString("locality"));

                suppliers.add( new Supplier(tva, name, street, streetNumber, phone, locality));
            }
            return suppliers;
        } catch (Exception e) {
            throw new DataAccessException("Lecture des fournisseurs");
        }
    }

    public Supplier getSupplier(String supplierName) throws DataAccessException {
        try {
            String tva;
            String name;
            String street;
            String streetNumber;
            String phone;
            Locality locality;
            ResultSet data;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from supplier where name = ?");
            statement.setString(1 , supplierName);
            data = statement.executeQuery();
            if(data.next()) {
                tva = data.getString("tva_number");
                name = data.getString("name");
                street = data.getString("street");
                streetNumber = data.getString("street_number");
                phone = data.getString("phone_number");
                locality = getLocality(data.getString("locality"));

                return new Supplier(tva, name, street, streetNumber, phone, locality);
            }
        } catch (Exception e) {
            throw new DataAccessException("Lecture fournisseur");
        }
        return null;
    }

    public Supplier getSupplierTva(String supplierTva) throws DataAccessException {
        try {
            String tva;
            String name;
            String street;
            String streetNumber;
            String phone;
            Locality locality;
            ResultSet data;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from supplier where tva_number = ?");

            statement.setString(1 , supplierTva);
            data = statement.executeQuery();
            if(data.next()) {
                tva = supplierTva;
                name = data.getString("name");
                street = data.getString("street");
                streetNumber = data.getString("street_number");
                phone = data.getString("phone_number");
                locality = getLocality(data.getString("locality"));

                return new Supplier(tva, name, street, streetNumber, phone, locality);
            }
        } catch (Exception e) {
            throw new DataAccessException("Lecture fournisseur (tva)");

        }
        return null;
    }

    public String[] getAllManufacturers() throws DataAccessException {
        try{
            ArrayList<String> list = new ArrayList<>();
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select name from manufacturer");
            ResultSet data = statement.executeQuery();

            while (data.next()){
                list.add(data.getString("name"));
            }
            return list.toArray(new String[list.size()]);
        }
        catch(DbConnectionException exception){
            throw new DataAccessException("Connexion à la source de stockage");
        } catch (SQLException exception) {
            throw new DataAccessException("Lecture du stockage");
        }
    }

    public String[] getAllModels(String manufacturer) throws DataAccessException {
        try {
            ArrayList<String> list = new ArrayList<>();
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select name from model where manufacturer = ?");
            ResultSet data;

            statement.setString(1, manufacturer);
            data = statement.executeQuery();

            while (data.next()) {
                list.add(data.getString("name"));
            }
            return list.toArray(new String[list.size()]);
        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
    }

    public Model getModel(String modelName) throws DataAccessException {
        try {
            String name;
            String manufacturerName;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from model where name = ?");
            ResultSet data;
            statement.setString(1 , modelName);
            data = statement.executeQuery();
            if(data.next()) {
                name = data.getString("name");
                manufacturerName = data.getString("manufacturer");

                return new Model(name, new Manufacturer(manufacturerName));
            }
        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return null;
    }

    public Model getModelWithVehicles(String modelName) throws DataAccessException {
        try {
            String name;
            String manufacturerName;
            ArrayList<Vehicle> vehicles;
            Model model;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from model where name = ?");
            ResultSet data;
            statement.setString(1 , modelName);
            data = statement.executeQuery();
            if(data.next()) {
                name = data.getString("name");
                manufacturerName = data.getString("manufacturer");
                vehicles = getAllVehiclesFromModel(modelName);
                model = new Model(name, new Manufacturer(manufacturerName) , vehicles);
                model.setModelInVehucles();

                return model;
            }
        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return null;
    }


    public ArrayList<Vehicle> getAllVehicles() throws DataAccessException{
        try {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            Integer mileage;
            Integer engineCylinder;
            Integer enginePower;
            String chassisNumber;
            String color;
            String notes;
            String engineEnergy;
            GregorianCalendar firstRegistrationDate;
            GregorianCalendar buyDate;
            Double buyPrice;
            Boolean hasImmatCertificate;
            Boolean hasTechnicalControl;
            Boolean hasConformityCertificate;
            Boolean isSellingOnAutoscout;
            Boolean isSellingOnFacebook;
            Boolean isSellingOnSecondHand;
            Boolean isOnSale;
            Model model;
            Supplier supplier;
            ArrayList<Bill> bills;
            Date date;
            Vehicle vehicle;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement;
            ResultSet data;
            statement = connection.prepareStatement("" +
                        "select vehicle.* " +
                        "from vehicle " +
                        "join model " +
                        "on vehicle.model = model.name " +
                        "join manufacturer " +
                        "on model.manufacturer = manufacturer.name " +
                        "order by vehicle.buy_date desc, manufacturer.name, vehicle.model, vehicle.first_registration_date;");
            data = statement.executeQuery();

            while(data.next()) {
                chassisNumber = data.getString("chassis_number");
                color = data.getString("color");

                date = data.getDate("first_registration_date");
                if(date == null) {
                    firstRegistrationDate = null;
                } else {
                    firstRegistrationDate = new GregorianCalendar();
                    firstRegistrationDate.setTime(new Date(date.getTime()));
                }

                engineCylinder = data.getInt("engine_cylinder");
                engineEnergy = data.getString("engine_energy");
                enginePower = data.getInt("engine_power");

                date = data.getDate("buy_date");
                buyDate = new GregorianCalendar();
                buyDate.setTime(new Date(date.getTime()));
                buyPrice = data.getDouble("buy_price");

                mileage = data.getInt("mileage");
                notes = data.getString("notes");

                isOnSale = data.getBoolean("is_on_sale");

                hasImmatCertificate = data.getBoolean("has_immat_certificate");
                hasTechnicalControl = data.getBoolean("has_technical_control");
                hasConformityCertificate = data.getBoolean("has_conformity_certificate");

                isSellingOnAutoscout = data.getBoolean("selling_on_autoscout");
                isSellingOnFacebook = data.getBoolean("selling_on_facebook");
                isSellingOnSecondHand = data.getBoolean("selling_on_second_hand");

                model = getModel(data.getString("model"));
                supplier = getSupplierTva(data.getString("tva_supplier"));
                bills = getAllBillsFromVehicle(chassisNumber);

                vehicle = new Vehicle(
                        chassisNumber,color, firstRegistrationDate, engineCylinder, engineEnergy, enginePower, buyPrice, buyDate, mileage,
                        notes, isOnSale, hasImmatCertificate, hasTechnicalControl, hasConformityCertificate,
                        isSellingOnAutoscout, isSellingOnFacebook, isSellingOnSecondHand, model, supplier, bills
                );
                vehicle.setVehicleBills();
                vehicles.add(vehicle);
            }
            return vehicles;

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
    }

    public Vehicle getVehicle(String chassisNumber) throws DataAccessException {

        try {
            Integer mileage;
            Integer engineCylinder;
            Integer enginePower;
            String chassisNumberDb;
            String color;
            String notes;
            String engineEnergy;
            GregorianCalendar firstRegistrationDate;
            GregorianCalendar buyDate;
            Double buyPrice;
            Boolean hasImmatCertificate;
            Boolean hasTechnicalControl;
            Boolean hasConformityCertificate;
            Boolean isSellingOnAutoscout;
            Boolean isSellingOnFacebook;
            Boolean isSellingOnSecondHand;
            Boolean isOnSale;
            Model model;
            Supplier supplier;
            ArrayList<Bill> bills;

            Date date;
            Vehicle vehicle;
            ResultSet data;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from vehicle where chassis_number = ?");

            statement.setString(1, chassisNumber);
            data = statement.executeQuery();
            if(data.next()) {
                chassisNumberDb = data.getString("chassis_number");
                color = data.getString("color");

                date = data.getDate("first_registration_date");
                if(date == null) {
                    firstRegistrationDate = null;
                } else {
                    firstRegistrationDate = new GregorianCalendar();
                    firstRegistrationDate.setTime(new Date(date.getTime()));
                }

                engineCylinder = data.getInt("engine_cylinder");
                engineEnergy = data.getString("engine_energy");
                enginePower = data.getInt("engine_power");

                date = data.getDate("buy_date");
                buyDate = new GregorianCalendar();
                buyDate.setTime(new Date(date.getTime()));
                buyPrice = data.getDouble("buy_price");

                mileage = data.getInt("mileage");
                notes = data.getString("notes");

                isOnSale = data.getBoolean("is_on_sale");

                hasImmatCertificate = data.getBoolean("has_immat_certificate");
                hasTechnicalControl = data.getBoolean("has_technical_control");
                hasConformityCertificate = data.getBoolean("has_conformity_certificate");

                isSellingOnAutoscout = data.getBoolean("selling_on_autoscout");
                isSellingOnFacebook = data.getBoolean("selling_on_facebook");
                isSellingOnSecondHand = data.getBoolean("selling_on_second_hand");

                model = getModel(data.getString("model"));
                supplier = getSupplierTva(data.getString("tva_supplier"));
                bills = getAllBillsFromVehicle(chassisNumberDb);

                vehicle = new Vehicle(
                        chassisNumber,color, firstRegistrationDate, engineCylinder, engineEnergy, enginePower, buyPrice, buyDate, mileage,
                        notes, isOnSale, hasImmatCertificate, hasTechnicalControl, hasConformityCertificate,
                        isSellingOnAutoscout, isSellingOnFacebook, isSellingOnSecondHand, model, supplier, bills
                  );
                vehicle.setVehicleBills();
                return vehicle;
                }
            return null;
            } catch (Exception e) {
                throw new DataAccessException("Lecture du stockage");
            }
    }

    public ArrayList<Vehicle> getAllVehiclesFromModel(String modelName) throws DataAccessException{
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            Integer mileage;
            Integer engineCylinder;
            Integer enginePower;
            String chassisNumber;
            String color;
            String notes;
            String engineEnergy;
            GregorianCalendar firstRegistrationDate;
            GregorianCalendar buyDate;
            Double buyPrice;
            Boolean hasImmatCertificate;
            Boolean hasTechnicalControl;
            Boolean hasConformityCertificate;
            Boolean isSellingOnAutoscout;
            Boolean isSellingOnFacebook;
            Boolean isSellingOnSecondHand;
            Boolean isOnSale;
            Model model;
            Supplier supplier;
            ArrayList<Bill> bills;
            Date date;

            Vehicle vehicle;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement;
            ResultSet data;
            statement = connection.prepareStatement("" +
                    "select * " +
                    "from vehicle " +
                    "where model = ?;");
            statement.setString(1, modelName);
            data = statement.executeQuery();

            while (data.next()) {
                chassisNumber = data.getString("chassis_number");
                color = data.getString("color");

                date = data.getDate("first_registration_date");
                if (date == null) {
                    firstRegistrationDate = null;
                } else {
                    firstRegistrationDate = new GregorianCalendar();
                    firstRegistrationDate.setTime(new Date(date.getTime()));
                }

                engineCylinder = data.getInt("engine_cylinder");
                engineEnergy = data.getString("engine_energy");
                enginePower = data.getInt("engine_power");

                date = data.getDate("buy_date");
                buyDate = new GregorianCalendar();
                buyDate.setTime(new Date(date.getTime()));
                buyPrice = data.getDouble("buy_price");

                mileage = data.getInt("mileage");
                notes = data.getString("notes");

                isOnSale = data.getBoolean("is_on_sale");

                hasImmatCertificate = data.getBoolean("has_immat_certificate");
                hasTechnicalControl = data.getBoolean("has_technical_control");
                hasConformityCertificate = data.getBoolean("has_conformity_certificate");

                isSellingOnAutoscout = data.getBoolean("selling_on_autoscout");
                isSellingOnFacebook = data.getBoolean("selling_on_facebook");
                isSellingOnSecondHand = data.getBoolean("selling_on_second_hand");

                supplier = getSupplierTva(data.getString("tva_supplier"));
                bills = getAllBillsFromVehicle(chassisNumber);

                vehicle = new Vehicle(
                        chassisNumber, color, firstRegistrationDate, engineCylinder, engineEnergy, enginePower, buyPrice, buyDate, mileage,
                        notes, isOnSale, hasImmatCertificate, hasTechnicalControl, hasConformityCertificate,
                        isSellingOnAutoscout, isSellingOnFacebook, isSellingOnSecondHand, null, supplier, bills
                );
                vehicle.setVehicleBills();
                vehicles.add(vehicle);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return vehicles;

    }

    public ArrayList<Vehicle> getAllVehiclesBetweenDates(java.util.Date firstDate, java.util.Date lastDate) throws DataAccessException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            Integer mileage;
            Integer engineCylinder;
            Integer enginePower;
            String chassisNumber;
            String color;
            String notes;
            String engineEnergy;
            GregorianCalendar firstRegistrationDate;
            GregorianCalendar buyDate;
            Double buyPrice;
            Boolean hasImmatCertificate;
            Boolean hasTechnicalControl;
            Boolean hasConformityCertificate;
            Boolean isSellingOnAutoscout;
            Boolean isSellingOnFacebook;
            Boolean isSellingOnSecondHand;
            Boolean isOnSale;
            Model model;
            Supplier supplier;
            ArrayList<Bill> bills;
            Date date;

            java.sql.Date firstDateSQL;
            java.sql.Date lastDateSQL;

            Vehicle vehicle;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement;
            ResultSet data;
            statement = connection.prepareStatement("" +
                    "select distinct vehicle.* " +
                    "from vehicle " +
                    "join model " +
                    "on vehicle.model = model.name " +
                    "left join bill " +
                    "on bill.vehicle_chassis = vehicle.chassis_number " +
                    "where vehicle.buy_date between ? and ? " +
                    "order by vehicle.buy_date desc, model.manufacturer, model.name, bill.price");

            firstDateSQL = new java.sql.Date(firstDate.getTime());
            statement.setDate(1, firstDateSQL);
            lastDateSQL =  new java.sql.Date(lastDate.getTime());
            statement.setDate(2, lastDateSQL);

            data = statement.executeQuery();

            while (data.next()) {
                chassisNumber = data.getString("chassis_number");
                color = data.getString("color");

                date = data.getDate("first_registration_date");
                if (date == null) {
                    firstRegistrationDate = null;
                } else {
                    firstRegistrationDate = new GregorianCalendar();
                    firstRegistrationDate.setTime(new Date(date.getTime()));
                }

                engineCylinder = data.getInt("engine_cylinder");
                engineEnergy = data.getString("engine_energy");
                enginePower = data.getInt("engine_power");

                date = data.getDate("buy_date");
                buyDate = new GregorianCalendar();
                buyDate.setTime(new Date(date.getTime()));
                buyPrice = data.getDouble("buy_price");

                mileage = data.getInt("mileage");
                notes = data.getString("notes");

                isOnSale = data.getBoolean("is_on_sale");

                hasImmatCertificate = data.getBoolean("has_immat_certificate");
                hasTechnicalControl = data.getBoolean("has_technical_control");
                hasConformityCertificate = data.getBoolean("has_conformity_certificate");

                isSellingOnAutoscout = data.getBoolean("selling_on_autoscout");
                isSellingOnFacebook = data.getBoolean("selling_on_facebook");
                isSellingOnSecondHand = data.getBoolean("selling_on_second_hand");

                model = getModel(data.getString("model"));
                supplier = getSupplierTva(data.getString("tva_supplier"));
                bills = getAllBillsFromVehicle(chassisNumber);

                vehicle = new Vehicle(
                        chassisNumber, color, firstRegistrationDate, engineCylinder, engineEnergy, enginePower, buyPrice, buyDate, mileage,
                        notes, isOnSale, hasImmatCertificate, hasTechnicalControl, hasConformityCertificate,
                        isSellingOnAutoscout, isSellingOnFacebook, isSellingOnSecondHand, model, supplier, bills
                );
                vehicle.setVehicleBills();
                vehicles.add(vehicle);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesSoldByLocality(String localityName) throws DataAccessException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            Integer mileage;
            Integer engineCylinder;
            Integer enginePower;
            String chassisNumber;
            String color;
            String notes;
            String engineEnergy;
            GregorianCalendar firstRegistrationDate;
            GregorianCalendar buyDate;
            Double buyPrice;
            Boolean hasImmatCertificate;
            Boolean hasTechnicalControl;
            Boolean hasConformityCertificate;
            Boolean isSellingOnAutoscout;
            Boolean isSellingOnFacebook;
            Boolean isSellingOnSecondHand;
            Boolean isOnSale;
            Model model;
            Supplier supplier;
            ArrayList<Bill> bills;
            Date date;

            Vehicle vehicle;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement;
            ResultSet data;
            statement = connection.prepareStatement("" +
                    "select * " +
                    "from vehicle v " +
                    "join bill b " +
                    "on v.chassis_number = b.vehicle_chassis " +
                    "join client c " +
                    "on b.client = c.client_id " +
                    "join locality l " +
                    "on c.locality = l.name " +
                    "where is_on_sale = false " +
                    "and b.is_sale = true " +
                    "and l.name = ? " +
                    "order by l.name, c.last_name, c.first_name, b.bill_date;");

           statement.setString(1, localityName);
            data = statement.executeQuery();

            while (data.next()) {
                chassisNumber = data.getString("chassis_number");
                color = data.getString("color");

                date = data.getDate("first_registration_date");
                if (date == null) {
                    firstRegistrationDate = null;
                } else {
                    firstRegistrationDate = new GregorianCalendar();
                    firstRegistrationDate.setTime(new Date(date.getTime()));
                }

                engineCylinder = data.getInt("engine_cylinder");
                engineEnergy = data.getString("engine_energy");
                enginePower = data.getInt("engine_power");

                date = data.getDate("buy_date");
                buyDate = new GregorianCalendar();
                buyDate.setTime(new Date(date.getTime()));
                buyPrice = data.getDouble("buy_price");

                mileage = data.getInt("mileage");
                notes = data.getString("notes");

                isOnSale = data.getBoolean("is_on_sale");

                hasImmatCertificate = data.getBoolean("has_immat_certificate");
                hasTechnicalControl = data.getBoolean("has_technical_control");
                hasConformityCertificate = data.getBoolean("has_conformity_certificate");

                isSellingOnAutoscout = data.getBoolean("selling_on_autoscout");
                isSellingOnFacebook = data.getBoolean("selling_on_facebook");
                isSellingOnSecondHand = data.getBoolean("selling_on_second_hand");

                model = getModel(data.getString("model"));
                supplier = getSupplierTva(data.getString("tva_supplier"));
                bills = getAllBillsFromVehicle(chassisNumber);

                vehicle = new Vehicle(
                        chassisNumber, color, firstRegistrationDate, engineCylinder, engineEnergy, enginePower, buyPrice, buyDate, mileage,
                        notes, isOnSale, hasImmatCertificate, hasTechnicalControl, hasConformityCertificate,
                        isSellingOnAutoscout, isSellingOnFacebook, isSellingOnSecondHand, model, supplier, bills
                );
                vehicle.setVehicleBills();
                vehicles.add(vehicle);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return vehicles;
    }

    public ArrayList<Bill> getBillsBetween2Dates(java.util.Date firstDate, java.util.Date lastDate, boolean isSaleSelected) throws DataAccessException {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            Date date;
            Integer id;
            Boolean isSale;
            Double billPrice;
            GregorianCalendar billDate;
            Vehicle vehicle;
            Client client;
            ArrayList<Payment> payments;

            Bill bill;

            java.sql.Date firstDateSQL;
            java.sql.Date lastDateSQL;

            ResultSet data;
            Connection connection = SingletonConnection.getInstance();
            String sql;
            PreparedStatement statement;

            if(isSaleSelected) {
                sql = "select * " +
                        "from bill b " +
                        "join client c " +
                        "on b.client = c.client_id " +
                        "join payment p " +
                        "on p.bill = b.bill_id " +
                        "where b.bill_date between ? and ? " +
                        "and is_sale = ? " +
                        "group by b.bill_id " +
                        "having b.price > sum(p.amount) " +
                        "order by b.bill_date desc, c.last_name asc, c.first_name asc;";
            } else {
                sql =  "select * from bill b  " +
                        "join client c  " +
                        "on b.client = c.client_id  " +
                        "join locality " +
                        "on c.locality = locality.name " +
                        "where b.bill_date between ? and ? " +
                        "and b.is_sale = ? " +
                        "order by b.bill_date desc, locality.name asc, c.last_name asc, c.first_name; ";
            }
            statement = connection.prepareStatement(sql);

            firstDateSQL = new java.sql.Date(firstDate.getTime());
            statement.setDate(1, firstDateSQL);
            lastDateSQL =  new java.sql.Date(lastDate.getTime());
            statement.setDate(2, lastDateSQL);
            statement.setBoolean(3,isSaleSelected);

            data = statement.executeQuery();
            while (data.next()) {
                id = data.getInt("bill_id");
                isSale = data.getBoolean("is_sale");
                billPrice = data.getDouble("price");
                date = data.getDate("bill_date");
                billDate = new GregorianCalendar();
                billDate.setTime(new Date(date.getTime()));
                vehicle = getVehicle(data.getString("vehicle_chassis"));
                client = getClient(data.getInt("client"));
                payments = getPaymentsFromBill(id);
                bill = new Bill(id, isSale, billPrice, billDate, vehicle, client, payments);
                bill.setPayments();
                bills.add(bill);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        if(isSaleSelected) {
            ArrayList<Bill> billsWithoutBill = getAllBillsWithoutPayment(firstDate,lastDate);
            for (Bill bill : bills) {
                billsWithoutBill.add(bill);
            }
            return billsWithoutBill;
        }
        return bills;
    }

    public ArrayList<Bill> getAllBillsWithoutPayment(java.util.Date firstDate, java.util.Date lastDate) throws DataAccessException {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            Date date;
            Integer id;
            Boolean isSale;
            Double billPrice;
            GregorianCalendar billDate;
            Vehicle vehicle;
            Client client;
            ArrayList<Payment> payments;
            Bill bill;

            java.sql.Date firstDateSQL;
            java.sql.Date lastDateSQL;

            ResultSet data;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement;

            statement = connection.prepareStatement("" +
                    "select b.* " +
                    "from bill b " +
                    "where b.bill_id not in(select bill_id from bill join payment on payment.bill = bill.bill_id) " +
                    "and b.bill_date between ? and ? " +
                    "and b.is_sale = 1;" +
                    "");

            firstDateSQL = new java.sql.Date(firstDate.getTime());
            statement.setDate(1, firstDateSQL);
            lastDateSQL =  new java.sql.Date(lastDate.getTime());
            statement.setDate(2, lastDateSQL);

            data = statement.executeQuery();
            while(data.next()) {
                id = data.getInt("bill_id");
                isSale = data.getBoolean("is_sale");
                billPrice = data.getDouble("price");
                date = data.getDate("bill_date");
                billDate = new GregorianCalendar();
                billDate.setTime(new Date(date.getTime()));
                vehicle = getVehicle(data.getString("vehicle_chassis"));
                client = getClient(data.getInt("client"));
                payments = getPaymentsFromBill(id);
                bill = new Bill(id, isSale, billPrice, billDate, vehicle, client, payments);
                bill.setPayments();
                bills.add(bill);
            }
        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return bills;
    }

    public ArrayList<Bill> getAllBillsFromVehicle(String vehicleChassis) throws DataAccessException {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            GregorianCalendar billDate;
            Client client;
            Date date;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from bill where vehicle_chassis = ?");
            ResultSet data;
            statement.setString(1, vehicleChassis);
            data = statement.executeQuery();

            while (data.next()) {
                date = data.getDate("bill_date");
                if(date == null) {
                    billDate = null;
                } else {
                    billDate = new GregorianCalendar();
                    billDate.setTime(new Date(date.getTime()));
                }
                client = getClient(data.getInt("client"));
                bills.add(new Bill(data.getBoolean("is_sale"), data.getDouble("price"), billDate, null, client));
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return bills;
    }

    public ArrayList<Bill> getAllBillsFromClient(Integer clientId) throws DataAccessException {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            GregorianCalendar billDate;
            Date date;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from bill where client = ?");
            ResultSet data;
            statement.setInt(1, clientId);
            data = statement.executeQuery();


            while (data.next()) {
                date = data.getDate("bill_date");
                if(date == null) {
                    billDate = null;
                } else {
                    billDate = new GregorianCalendar();
                    billDate.setTime(new Date(date.getTime()));
                }
                bills.add(new Bill(data.getBoolean("is_sale"), data.getDouble("price"),
                        billDate, getVehicle(data.getString("vehicle_chassis")), null));
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return bills;
    }

    public ArrayList<Client> getAllClients()  throws DataAccessException {
        ArrayList<Client> clients = new ArrayList<>();
        try{

            Integer id;
            String lastName;
            String firstName;
            String street;
            String streetNumber;
            String phoneNumber;
            String tvaNumber;
            ArrayList<Bill> bills;
            Locality locality;

            Client client;

            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from client order by last_name, first_name");
            ResultSet data;
            data = statement.executeQuery();
            while (data.next()) {
                id = data.getInt("client_id");
                lastName = data.getString("last_name");
                firstName = data.getString("first_name");
                street = data.getString("street");
                streetNumber = data.getString("street_number");
                phoneNumber = data.getString("phone_number");
                tvaNumber = data.getString("tva_number");
                locality = getLocality(data.getString("locality"));
                bills = getAllBillsFromClient(data.getInt("client_id"));

                client = new Client(id ,lastName, firstName, street, streetNumber, phoneNumber, tvaNumber, locality, bills);
                client.setClientBills();
                clients.add(client);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return clients;
    }

    public Client getClient(Integer clientId) throws  DataAccessException {
        try {
            Integer id;
            String lastName;
            String firstName;
            String street;
            String streetNumber;
            String phoneNumber;
            String tvaNumber;
            Locality locality;

            Client client;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * " +
                    "from client " +
                    "where client_id = ? ;"
            );
            ResultSet data;
            statement.setInt(1, clientId);
            data = statement.executeQuery();
            if(data.next()) {
                id = data.getInt("client_id");
                lastName = data.getString("last_name");
                firstName = data.getString("first_name");
                street = data.getString("street");
                streetNumber = data.getString("street_number");
                phoneNumber = data.getString("phone_number");
                tvaNumber = data.getString("tva_number");
                locality = getLocality(data.getString("locality"));
                client = new Client(id, lastName, firstName,street,streetNumber,phoneNumber,tvaNumber,locality);
                return client;
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return null;
    }
/*
    public Client getClientWithBills(Integer clientId) throws DataAccessException {
        try {
            Integer id;
            String lastName;
            String firstName;
            String street;
            String streetNumber;
            String phoneNumber;
            String tvaNumber;
            ArrayList<Bill> bills;
            Locality locality;

            Client client;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * " +
                    "from client " +
                    "where client_id = ? ;"
            );
            ResultSet data;
            statement.setInt(1, clientId);
            data = statement.executeQuery();
            if(data.next()) {
                id = data.getInt("client_id");
                lastName = data.getString("last_name");
                firstName = data.getString("first_name");
                street = data.getString("street");
                streetNumber = data.getString("street_number");
                phoneNumber = data.getString("phone_number");
                tvaNumber = data.getString("tva_number");
                locality = getLocality(data.getString("locality"));
                bills = getAllBillsFromClient(clientId);
                client = new Client(id, lastName, firstName,street,streetNumber,phoneNumber,tvaNumber,locality,bills);
                client.setClientBills();
                return client;
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return null;
    }
*/
    public ArrayList<Bill> getAllBills() throws DataAccessException {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            Date date;
            Integer id;
            Boolean isSale;
            Double billPrice;
            GregorianCalendar billDate;
            Vehicle vehicle;
            Client client;
            ArrayList<Payment> payments;
            Bill bill;

            ResultSet data;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * " +
                    "from bill " +
                    "order by bill_date desc, bill_id" +
                    "");
            data = statement.executeQuery();
            while (data.next()) {
                id = data.getInt("bill_id");
                isSale = data.getBoolean("is_sale");
                billPrice = data.getDouble("price");
                date = data.getDate("bill_date");
                billDate = new GregorianCalendar();
                billDate.setTime(new Date(date.getTime()));
                vehicle = getVehicle(data.getString("vehicle_chassis"));
                client = getClient(data.getInt("client"));
                payments = getPaymentsFromBill(id);
                bill = new Bill(id, isSale, billPrice, billDate, vehicle, client, payments);
                bill.setPayments();
                bills.add(bill);
            }

        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return bills;
     }

     public ArrayList<Payment> getPaymentsFromBill(Integer billId) throws DataAccessException {
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            String type;
            Double amount;
            String checkNumber;

            ResultSet data;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("select * from payment where bill = ?; ");
            statement.setInt(1, billId);
            data = statement.executeQuery();

            while(data.next()) {
                type = data.getString("type");
                amount = data.getDouble("amount");
                checkNumber = data.getString("check_number");
                payments.add(new Payment(type, amount, checkNumber));
            }
        } catch (Exception e) {
            throw new DataAccessException("Lecture du stockage");
        }
        return payments;
     }

    // Adds
    public void addSupplier(Supplier supplier) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into supplier(tva_number, name, street, street_number, phone_number, locality) " +
                    "values (?,?,?,?,?,?)");
            statement.setString(1, supplier.getTvaNumber());
            statement.setString(2, supplier.getName());
            statement.setString(3, supplier.getStreet());
            statement.setString(4, supplier.getStreetNumber());
            statement.setString(5, supplier.getPhoneNumber());
            statement.setString(6, supplier.getLocality().getName());
            statement.execute();
        } catch (Exception e) {
             throw new DataAccessException("Insertion dans le stockage");
        }
    }

    public void addModel(String manufacturerName, String newModel) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into model(manufacturer, name) " +
                    "values (?, ?)");
            statement.setString(1, manufacturerName);
            statement.setString(2, newModel);
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException("Insertion dans le stockage");
        }
    }

    public void addVehicle(Vehicle vehicle) throws DataAccessException {
        try {
            Date date;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into vehicle(chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, " +
                    "buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, " +
                    "selling_on_second_hand, selling_on_autoscout, model, tva_supplier) " +
                    "values (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, vehicle.getChassisNumber());
            if(vehicle.getColor() == null) { //TODO Toujours tester si c'est un facultatif ?
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, vehicle.getColor());
            }

            if(vehicle.getFirstRegistractionDate() == null) {
                statement.setNull(3, Types.DATE);
            } else {
                date = new Date(vehicle.getFirstRegistractionDate().getTimeInMillis());
                statement.setDate(3, date);
            }

            if (vehicle.getEngineCylinder() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, vehicle.getEngineCylinder());
            }
            statement.setString(5,vehicle.getEngineEnergy());

            if (vehicle.getEnginePower() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, vehicle.getEnginePower());
            }

            if (vehicle.getBuyPrice() == null) {
                statement.setNull(7, Types.DOUBLE);
            } else {
                statement.setDouble(7, vehicle.getBuyPrice());
            }
            date = new Date(new GregorianCalendar().getTimeInMillis());
            statement.setDate(8, date);
            if (vehicle.getMileage() == null) {
                statement.setNull(9, Types.INTEGER);
            } else {
                statement.setInt(9, vehicle.getMileage());
            }
            if (vehicle.getNotes() == null) {
                statement.setNull(10, Types.VARCHAR);
            } else {
                statement.setString(10, vehicle.getNotes());
            }
            statement.setBoolean(11, true);
            statement.setBoolean(12, vehicle.getHasImmatCertificate());
            statement.setBoolean(13, vehicle.getHasTechnicalControl());
            statement.setBoolean(14, vehicle.getHasConformityCertificate());
            statement.setBoolean(15, vehicle.getIsSellingOnFacebook());
            statement.setBoolean(16, vehicle.getIsSellingOnSecondHand());
            statement.setBoolean(17, vehicle.getIsSellingOnAutoscout());
            statement.setString(18,vehicle.getModel().getName());
            if(vehicle.getSupplier() == null) statement.setNull(19, Types.VARCHAR);
            else statement.setString(19,vehicle.getSupplier().getTvaNumber());
            statement.execute();

        } catch (Exception e) {
            throw new DataAccessException("Insertion dans le stockage");
        }
    }

    public void addBill(Bill bill) throws DataAccessException {
        try {
            Date date;
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into bill(is_sale, bill_date, price, vehicle_chassis, client) " +
                    "values (?, ?, ?, ?, ?)");
            statement.setBoolean(1, bill.isSale());
            if(bill.getBillDate() == null) {
                statement.setNull(2, Types.DATE);
            } else {
                date = new Date(bill.getBillDate().getTimeInMillis());
                statement.setDate(2, date);
            }
            statement.setDouble(3, bill.getBillPrice());
            statement.setString(4, bill.getVehicle().getChassisNumber());
            statement.setInt(5, bill.getClient().getId());
            statement.execute();
            updateVehicleState(bill.getVehicle().getChassisNumber(), false);
        } catch (Exception e) {
            throw new DataAccessException("Insertion dans le stockage");
        }
    }

    // Updates
    public void updateSupplier(Supplier supplier) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "update supplier set " +
                    "name = ?, " +
                    "street = ?, " +
                    "street_number = ?, " +
                    "phone_number = ?, " +
                    "locality = ? " +
                    "where tva_number = ?;");
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getStreet());
            statement.setString(3, supplier.getStreetNumber());
            statement.setString(4, supplier.getPhoneNumber());
            statement.setString(5, supplier.getLocality().getName());
            statement.setString(6, supplier.getTvaNumber());
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException("Modification dans le stockage");
        }
    }

    public void updateVehicle(Vehicle vehicle) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "update vehicle set " +
                    "chassis_number = ?, " +
                    "color = ?, " +
                    "first_registration_date = ?, " +
                    "engine_cylinder = ?, " +
                    "engine_energy = ?, " +
                    "engine_power = ?, " +
                    "buy_price = ?, " +
                    "mileage = ?, " +
                    "notes = ?, " +
                    "is_on_sale = ?, " +
                    "has_immat_certificate = ?, " +
                    "has_technical_control = ?, " +
                    "has_conformity_certificate = ?, " +
                    "selling_on_facebook = ?, " +
                    "selling_on_second_hand = ?, " +
                    "selling_on_autoscout = ?, " +
                    "model = ?, " +
                    "tva_supplier = ? " +
                    "where chassis_number = ?"
            );
            statement.setString(1, vehicle.getChassisNumber());
            if (vehicle.getColor() == null) { //TODO Toujours tester si c'est un facultatif ?
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, vehicle.getColor());
            }

            if (vehicle.getFirstRegistractionDate() == null) {
                statement.setNull(3, Types.DATE);
            } else {
                Date sqlDateRegistration = new Date(vehicle.getFirstRegistractionDate().getTimeInMillis());
                statement.setDate(3, sqlDateRegistration);
            }

            if (vehicle.getEngineCylinder() == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, vehicle.getEngineCylinder());
            }
            statement.setString(5, vehicle.getEngineEnergy());

            if (vehicle.getEnginePower() == null) {
                statement.setNull(6, Types.INTEGER);
            } else {
                statement.setInt(6, vehicle.getEnginePower());
            }

            if (vehicle.getBuyPrice() == null) {
                statement.setNull(7, Types.DOUBLE);
            } else {
                statement.setDouble(7, vehicle.getBuyPrice());
            }
            if (vehicle.getMileage() == null) {
                statement.setNull(8, Types.INTEGER);
            } else {
                statement.setInt(8, vehicle.getMileage());
            }
            if (vehicle.getNotes() == null) {
                statement.setNull(9, Types.VARCHAR);
            } else {
                statement.setString(10, vehicle.getNotes());
            }
            statement.setBoolean(10, vehicle.getOnSale());
            statement.setBoolean(11, vehicle.getHasImmatCertificate());
            statement.setBoolean(12, vehicle.getHasTechnicalControl());
            statement.setBoolean(13, vehicle.getHasConformityCertificate());
            statement.setBoolean(14, vehicle.getIsSellingOnFacebook());
            statement.setBoolean(15, vehicle.getIsSellingOnSecondHand());
            statement.setBoolean(16, vehicle.getIsSellingOnAutoscout());
            statement.setString(17, vehicle.getModel().getName());
            if (vehicle.getSupplier() == null) {
                statement.setNull(18, Types.VARCHAR);
            } else {
                statement.setString(18, vehicle.getSupplier().getTvaNumber());
            }
            statement.setString(19, vehicle.getChassisNumber());
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException("Modification dans le stockage");
        }
    }
    public void updateVehicleState(String chassisNumber, boolean isOnSale) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "update vehicle " +
                    "set is_on_sale = ? " +
                    "where chassis_number = ? ;" +
                    "");
            statement.setBoolean(1 , isOnSale);
            statement.setString(2, chassisNumber);
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException("Modification dans le stockage");
        }
    }

    // Delete
    public void deleteSupplier(Supplier supplier) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from supplier " +
                    "where tva_number = ?;" +
                    "");
            statement.setString(1, supplier.getTvaNumber());
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException("Insertion dans le stockage");
        }
    }

    public void deleteModel(String modelName) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement("" +
                    " delete from model " +
                    "where name = ?;" +
                    "");
            statement.setString(1, modelName);
            statement.execute();
        } catch (Exception e) {
            throw new DataAccessException("Insertion dans le stockage");
        }
    }

    public void deleteVehicle(String vehicleChassis) throws DataAccessException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sql;
            PreparedStatement statement;

            // Supression du véhicule
                // Facture et payement => on cascade
            sql = "delete from vehicle where chassis_number = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,vehicleChassis);
            statement.execute();

        } catch (Exception e) {
            throw new DataAccessException("Supprimer Véhicule");
        }
    }



    public void deleteClient(Client client) {
        // TODO
    }
}
