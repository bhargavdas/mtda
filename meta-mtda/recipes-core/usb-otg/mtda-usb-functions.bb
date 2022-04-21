DESCRIPTION        = "USB functions for MTDA"
HOMEPAGE           = "https://github.com/siemens/mtda"

LICENSE            = "MIT"
LIC_FILES_CHKSUM   = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESPATH:prepend := "${THISDIR}/${PN}:"
RDEPENDS:${PN}     = "python3"
SRC_URI            = "file://mtda-usb-functions.service \
                      file://usb-functions              \
"

inherit systemd

S = "${WORKDIR}"

do_install() {
    install -m 0755 -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/mtda-usb-functions.service ${D}${systemd_system_unitdir}
    install -m 0755 -d ${D}/${libexecdir}/mtda/
    install -m 0755 ${S}/usb-functions ${D}/${libexecdir}/mtda/
}

SYSTEMD_SERVICE:${PN} = "mtda-usb-functions.service"
