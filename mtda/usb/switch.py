import abc

class UsbSwitch(object):
    __metaclass__ = abc.ABCMeta

    POWERED_OFF = 0
    POWERED_ON  = 1

    @abc.abstractmethod
    def configure(self, conf):
        """ Configure this USB switch from the provided configuration"""
        return

    @abc.abstractmethod
    def probe(self):
        """ Check presence of the USB switch"""
        return

    @abc.abstractmethod
    def on(self):
        """ Power on the target USB port"""
        return

    @abc.abstractmethod
    def off(self):
        """ Power off the target USB port"""
        return

    @abc.abstractmethod
    def status(self):
        """ Determine the current power state of the USB port"""
        return POWERED_OFF
