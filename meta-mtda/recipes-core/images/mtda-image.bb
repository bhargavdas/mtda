SUMMARY = "Custom image for mtda"
DESCRIPTION = "Custom image for mtda."

# Default device/file to use for our USB Mass Storage Gadget
#MTDA_MASS_STORAGE_FILE ??= "sda"

# based on core image base
include recipes-core/images/core-image-base.bb

DISTRO_FEATURES:append = " systemd"
VIRTUAL-RUNTIME_init_manager += "systemd"

#inherit extrausers
#EXTRA_USERS_PARAMS = "useradd -P mtda mtda;"

IMAGE_INSTALL:append = " \
        git \
        zstandard \
        zerorpc-python \
        mtda \
        python3-daemon \ 
        python3-zopeinterface \
        openssh \
        mtda-usb-functions \
        sd-mux-ctrl \
        pduclient \
        python3-zeroconf \
        python3-py3qterm \
        mjpg-streamer \
        usb-ms-use-storage-config \
        usb-ms-use-storage-service \
"
