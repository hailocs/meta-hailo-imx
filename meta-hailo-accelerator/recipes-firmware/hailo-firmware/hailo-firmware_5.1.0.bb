DESCRIPTION = "hailo firmware \
               hailo10 chip firmware (multiple files) \
               the recipe copies the files to /lib/firmware/hailo/hailo10 on the target device’s root file system"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=fe0008491325330bb9b8c55e09c49508"


SRC_URI = "https://hailo-csdata.s3.eu-west-2.amazonaws.com/resources/firmware/hailo10h/5.1.0/hailo10h_firmware_5.1.0.zip"
SRC_URI[md5sum] = "634610c3d4499fc0729514b6f3410718"


S = "${WORKDIR}/"

do_unpack[depends] += "unzip-native:do_populate_sysroot"

do_install() {
    install -d ${D}/lib/firmware/hailo/hailo10h
    install -m 0644 ${S}/customer_certificate.bin ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/fitImage ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/image-fs ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/scu_fw.bin ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-0.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-1.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-3.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-4.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-5.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-6.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-default.dtb.signed ${D}/lib/firmware/hailo/hailo10h/
    install -m 0644 ${S}/u-boot-spl.bin ${D}/lib/firmware/hailo/hailo10h/
}

FILES_${PN} += "/lib/firmware/hailo/"
