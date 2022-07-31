# ---------------------------------------------------------------------------
# This Isar layer is part of MTDA
# Copyright (C) 2022 Siemens Digital Industries Software
# ---------------------------------------------------------------------------
# SPDX-License-Identifier: MIT
# ---------------------------------------------------------------------------

inherit image

DESCRIPTION = "Debian image for MTDA assist boards"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${LAYERDIR_core}/licenses/COPYING.MIT;md5=838c366f69b72c5df05c96dff79b35f2"

PV = "1.0"

ISAR_RELEASE_CMD = "git -C ${LAYERDIR_mtda} describe --tags --dirty --match 'v[0-9].[0-9]*'"

# MTDA Package Feeds
MTDA_APT_URI ??= "https://apt.fury.io/mtda/"

# Build and deploy our MTDA packages
DEPENDS += "mtda-packages"
do_build[deptask] += "do_deploy_deb"

# We suffix u-boot-script with DISTRO and MACHINE, remove the non-suffixed
# version of the package
IMAGE_INSTALL_remove = "u-boot-script"

# Custom u-boot script for the beaglebone-black
IMAGE_INSTALL_append_beaglebone-black = " u-boot-script-${DISTRO}-${MACHINE}"

# Custom u-boot script for the nanopi-neo
IMAGE_INSTALL_append_nanopi-neo = " u-boot-script-${DISTRO}-${MACHINE}"

# Custom u-boot and script for the nanopi-r1
DEPENDS_append_nanopi-r1 = " u-boot-nanopi-r1"
IMAGE_INSTALL_append_nanopi-r1 = " u-boot-script-${DISTRO}-${MACHINE}"

IMAGE_INSTALL += "                       \
    mtda-hostname                        \
    local-settings                       \
    sshd-regen-keys                      \
"

IMAGE_PREINSTALL += "                    \
    iproute2                             \
    isc-dhcp-client                      \
    mjpg-streamer                        \
    mtda                                 \
    network-manager                      \
    pdudaemon-client                     \
    sd-mux-ctrl                          \
    ssh                                  \
    sudo                                 \
    vim                                  \
    wireless-regdb                       \
    wireless-tools                       \
    bluetooth                            \
    python3-libgpiod                     \
"

# HomeKit support
IMAGE_PREINSTALL += "python3-hap-python"

# LAVA support
IMAGE_PREINSTALL += "lava-dispatcher"

# Expand root file-system
IMAGE_INSTALL_append = " expand-on-first-boot "

# Remove meta-isar examples if they are there...
IMAGE_INSTALL_remove = "          \
    example-module-${KERNEL_NAME} \
    example-raw                   \
    hello-isar                    \
    samefile                      \
"

# Create a "mtda" user account with "mtda" as the default password
# hash created with: python3 -c 'import crypt; print(crypt.crypt("mtda", crypt.mksalt(crypt.METHOD_SHA512)))'
USERS += "mtda"
GROUPS += "mtda"
USER_mtda[gid] = "mtda"
USER_mtda[home] = "/home/mtda"
USER_mtda[comment] = "Multi-Tenant Device Access"
USER_mtda[flags] = "system create-home"
USER_mtda[groups] = "mtda sudo"
USER_mtda[password] ??= "$6$uaP1WXXu/joK8zxJ$LONexagmcWBKkY/HRQe0fVjY7n06FkX1qJUjigQ.4JVYxC9/OfBu3iJrF8hugMo2CaIh1sIOxDdpXvWWIjhfQ1"
USER_mtda[shell] = "/bin/bash"

# Remove the "isar" accounts
GROUPS_remove = "isar"
USERS_remove  = "isar"

# Add mtda package feeds to /etc/apt/sources.list.d/
ROOTFS_POSTPROCESS_COMMAND += "mtda_add_apt_sources"
mtda_add_apt_sources() {
    echo "deb [trusted=yes] ${MTDA_APT_URI} /" \
        | sudo tee -a ${IMAGE_ROOTFS}/etc/apt/sources.list.d/mtda.list
}
