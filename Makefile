# sources input dir
SOURCE_DIR = Android/FoodTruckr/app/src/main/java/com/brianjmelton/foodtruckr/shared
# root output dir
BUILD_DIR = iOS/FoodTruckr/FoodTruckr/Classes/Shared
# output dir for translations
TRANSLATIONS_DIR = $(BUILD_DIR)/translations
# output dir for compilations
COMPILE_DIR = $(BUILD_DIR)/out
OUTFILE = /tipcalc.o

# j2objc setup
J2OBJC_DISTRIBUTION = ~/j2objc-0.9.5
J2OBJC = $(J2OBJC_DISTRIBUTION)/j2objc
J2OBJCC = $(J2OBJC_DISTRIBUTION)/j2objcc

clean:
	@echo cleaning $(BUILD_DIR)/...
	@rm -rf $(BUILD_DIR)/
	@echo
	@echo all clean!

translate:
	@echo translating sources...
	$(J2OBJC) -use-arc --verbose -sourcepath $(SOURCE_DIR) -d $(TRANSLATIONS_DIR) $(SOURCE_DIR)/*/*\.java
	@echo
	@echo translation complete

compile:
	@echo creating output directories...
	@mkdir -p $(COMPILE_DIR)
	@echo compiling translations...
	@echo
	@cd $(COMPILE_DIR); \
	    $(J2OBJCC) -v -c ../../../$(TRANSLATIONS_DIR)/*\.m
	@echo compilation complete
