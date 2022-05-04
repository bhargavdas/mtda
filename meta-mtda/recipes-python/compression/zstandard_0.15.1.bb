SUMMARY = "Python bindings for interfacing with the Zstandard compression library"
DESCRIPTION = "Python bindings for interfacing with the Zstandard compression library. A C extension and CFFI interface are provided."
HOMEPAGE = "https://github.com/indygreg/python-zstandard"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3ae87c50fd64b6f0942823686871e758"

SRC_URI[sha256sum] = "cb7c6a6f7d62350b9f5539045da54422975630e34dd9069584cc776b9917115f"

inherit pypi setuptools3

PYPI_PACKAGE = "zstandard"