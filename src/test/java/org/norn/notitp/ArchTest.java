package org.norn.notitp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.norn.notitp");

        noClasses()
            .that()
            .resideInAnyPackage("org.norn.notitp.service..")
            .or()
            .resideInAnyPackage("org.norn.notitp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.norn.notitp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
