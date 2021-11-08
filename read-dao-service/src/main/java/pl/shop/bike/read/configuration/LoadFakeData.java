//package pl.shop.bike.read.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import pl.shop.bike.models.model.entities.BikeEntity;
//import pl.shop.bike.models.model.entities.ImageEntity;
//import pl.shop.bike.models.model.entities.accessories.BagsEntity;
//import pl.shop.bike.models.model.entities.accessories.BottlesEntity;
//import pl.shop.bike.models.model.entities.accessories.FendersEntity;
//import pl.shop.bike.models.model.entities.accessories.PumpEntity;
//import pl.shop.bike.models.model.entities.bikeParts.BrakeEntity;
//import pl.shop.bike.models.model.entities.bikeParts.DriveEntity;
//import pl.shop.bike.models.model.entities.bikeParts.FrameEntity;
//import pl.shop.bike.models.model.entities.workshop.MaintenanceEntity;
//import pl.shop.bike.models.model.entities.workshop.RacksEntity;
//import pl.shop.bike.models.model.entities.workshop.ToolsEntity;
//import pl.shop.bike.models.model.enums.BikePartsType;
//import pl.shop.bike.models.model.enums.BikeType;
//import pl.shop.bike.models.model.enums.ItemType;
//import pl.shop.commons.dao.BikesRepository;
//import pl.shop.commons.dao.accessoriesDAO.BagsRepository;
//import pl.shop.commons.dao.accessoriesDAO.BottlesRepository;
//import pl.shop.commons.dao.accessoriesDAO.FendersRepository;
//import pl.shop.commons.dao.accessoriesDAO.PumpsRepository;
//import pl.shop.commons.dao.workshopDAO.MaintenanceRepository;
//import pl.shop.commons.dao.workshopDAO.RacksRepository;
//import pl.shop.commons.dao.workshopDAO.ToolsRepository;
//
//import java.util.ArrayList;
//
//@Component
//public class LoadFakeData implements CommandLineRunner {
//
//    @Autowired
//    BikesRepository bikesRepository;
//
//    @Autowired
//    PumpsRepository pumpsRepository;
//
//    @Autowired
//    FendersRepository fendersRepository;
//
//    @Autowired
//    BottlesRepository bottlesRepository;
//
//    @Autowired
//    BagsRepository bagsRepository;
//
//    @Autowired
//    MaintenanceRepository maintenanceRepository;
//
//    @Autowired
//    RacksRepository racksRepository;
//
//    @Autowired
//    ToolsRepository toolsRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//
//        BrakeEntity brakeEntity = BrakeEntity.builder()
//                .id(null)
//                .name("Hamulec testowy")
//                .price(5000)
//                .mark("KANDS")
//                .bikePartsType(BikePartsType.BRAKE)
//                .color(null)
//                .description("To jest testowy hamulec")
//                .productCode("123DADA")
//                .weight(500.0)
//                .itemType(ItemType.PARTS)
//                .images(new ArrayList<>())
//                .build();
//        DriveEntity driveEntity = DriveEntity.builder()
//                .id(null)
//                .name("Napęd testowy")
//                .price(5000)
//                .mark("KANDS")
//                .bikePartsType(BikePartsType.DRIVE)
//                .color(null)
//                .description("To jest testowy hamulec")
//                .productCode("123DADA")
//                .weight(500.0)
//                .itemType(ItemType.PARTS)
//                .images(new ArrayList<>())
//                .build();
//
//        FrameEntity frameEntity = FrameEntity.builder()
//                .id(null)
//                .name("Rama testowa")
//                .price(5000)
//                .mark("KANDS")
//                .bikePartsType(BikePartsType.FRAME)
//                .color(null)
//                .description("To jest testowy hamulec")
//                .productCode("123DADA")
//                .weight(500.0)
//                .itemType(ItemType.PARTS)
//                .images(new ArrayList<>())
//                .build();
//
//        ImageEntity imageEntity5 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//        ImageEntity imageEntity6 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//        ImageEntity imageEntity7 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//
//        brakeEntity.getImages().add(imageEntity5);
//        driveEntity.getImages().add(imageEntity6);
//        frameEntity.getImages().add(imageEntity7);
//
//
//        BikeEntity bike = BikeEntity.builder()
//                .id(null)
//                .name("Rower testowy")
//                .description("Opis testowy")
//                .price(100.0)
//                .mark("KANDS")
//                .color("red")
//                .bikeType(BikeType.BMX)
//                .bikeCode("1234DADA")
//                .brake(brakeEntity)
//                .drive(driveEntity)
//                .frame(frameEntity)
//                .images(new ArrayList<>())
//                .itemType(ItemType.BIKES)
//                .build();
//
//
//        ImageEntity imageEntity = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//        bike.getImages().add(imageEntity);
//
//        ImageEntity imageEntity1 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//
//        ImageEntity imageEntity2 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//
//        ImageEntity imageEntity3 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//
//        ImageEntity imageEntity4 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//
//
//        PumpEntity pumpEntity = PumpEntity.builder()
//                        .name("Pompeczka testowa")
//                .itemType(ItemType.ACCESSORIES)
//                .images(new ArrayList<>())
//                                .build();
//        BottlesEntity bottlesEntity = BottlesEntity.builder()
//                .itemType(ItemType.ACCESSORIES)
//                        .name("Testowa butla")
//                .       images(new ArrayList<>())
//                                .build();
//        FendersEntity fendersEntity = FendersEntity.builder()
//                .itemType(ItemType.ACCESSORIES)
//                .name("Błotnik testowy")
//                .images(new ArrayList<>())
//                .build();
//        BagsEntity bagsEntity = BagsEntity.builder()
//                .itemType(ItemType.ACCESSORIES)
//                .name("Troba i sakwa")
//                .images(new ArrayList<>())
//                .build();
//
//
//        pumpEntity.getImages().add(imageEntity1);
//        bottlesEntity.getImages().add(imageEntity2);
//        fendersEntity.getImages().add(imageEntity3);
//        bagsEntity.getImages().add(imageEntity4);
//
//        pumpsRepository.save(pumpEntity);
//        bottlesRepository.save(bottlesEntity);
//        fendersRepository.save(fendersEntity);
//        bagsRepository.save(bagsEntity);
//
//        bikesRepository.save(bike);
//
//
//        ImageEntity imageEntity9 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//        ImageEntity imageEntity10 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//        ImageEntity imageEntity11 = ImageEntity.builder()
//                .id(null)
//                .imageUrl("https://res.cloudinary.com/do2tszehh/image/upload/v1630562399/Funnydog.jpg")
//                .build();
//
//        MaintenanceEntity maintenanceEntity = MaintenanceEntity.builder()
//                .name("testowy produkt")
//                .images(new ArrayList<>())
//                .itemType(ItemType.WORKSHOP)
//                .build();
//        RacksEntity racksEntity = RacksEntity.builder()
//                .name("testowy stojak rowerowy")
//                .images(new ArrayList<>())
//                .itemType(ItemType.WORKSHOP)
//                .build();
//        ToolsEntity toolsEntity = ToolsEntity.builder()
//                .name("Testowe narzędzia")
//                .images(new ArrayList<>())
//                .itemType(ItemType.WORKSHOP)
//                .build();
//
//        maintenanceEntity.getImages().add(imageEntity11);
//        racksEntity.getImages().add(imageEntity9);
//        toolsEntity.getImages().add(imageEntity10);
//
//        maintenanceRepository.save(maintenanceEntity);
//        racksRepository.save(racksEntity);
//        toolsRepository.save(toolsEntity);
//
//    }
//
//
//}
