SUMMARY = "Multi-Tenant Device Access"
DESCRIPTION = "Multi-Tenant Device Access (or MTDA for short) is a relatively small Python application acting as an interface to a test device"
HOMEPAGE = "https://github.com/siemens/mtda "

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ed3c91908f8cbdb153f02d73272aa17"

SRCREV	 = "7f7059c5ac49333cb7bc78cebf56c06d8d5be49a"

SRC_URI += "git://git@github.com/siemens/mtda.git;protocol=https;branch=master"

S        = "${WORKDIR}/git"

inherit setuptools3 systemd

RDEPENDS:${PN} = " \
    ${PYTHON_PN}-pyserial \
    ${PYTHON_PN}-pyusb \
    ${PYTHON_PN}-pyzmq \
    ${PYTHON_PN}-psutil \
    ${PYTHON_PN}-requests \
    ${PYTHON_PN}-terminal \
"

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/debian/mtda.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/debian/mtda-config.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/debian/mtda-config.path ${D}${systemd_system_unitdir}
}

SYSTEMD_SERVICE:${PN} = "mtda.service mtda-config.service mtda-config.path"
