DESCRIPTION        = "use /dev/mmcblk0 as device for our USB Mass Storage function"
MAINTAINER         = "Cedric Hombourger <cedric_hombourger@mentor.com>"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI            = "file://10-after-mmcblk0.conf"
FILESPATH:prepend := "${THISDIR}/${PN}:"

MTDA_MASS_STORAGE_FILE ??= "mmcblk0"

do_install:append() {
    cd ${WORKDIR}
    echo "MASS_STORAGE_FILE=/dev/${MTDA_MASS_STORAGE_FILE}" > usb-functions
    install -m 0755 -d            ${D}/etc/mtda/
    install -m 0644 usb-functions ${D}/etc/mtda/
    if [ "${MTDA_MASS_STORAGE_FILE}" != "mmcblk0" ];then
        sed -i -e "s/dev\-mmcblk0\.device/dev\-${MTDA_MASS_STORAGE_FILE}\.device/g" 10-after-mmcblk0.conf
        mv 10-after-mmcblk0.conf 10-after-${MTDA_MASS_STORAGE_FILE}.conf	
    fi
    install -m 0755 -d                    ${D}${systemd_system_unitdir}/mtda-usb-functions.service.d/
    install -m 0644 *.conf                ${D}${systemd_system_unitdir}/mtda-usb-functions.service.d/
}

PACKAGES += "${PN}-config ${PN}-service"
FILES:${PN}-config = " /etc/mtda/"
FILES:${PN}-service = "${systemd_system_unitdir}/mtda-usb-functions.service.d/"
