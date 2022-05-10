import pl.pak.rover.Coordinates
import pl.pak.rover.Rover
import pl.pak.rover.UnknownCommandException
import spock.lang.Specification

class CoordinatesAndDIrectionTestSpock extends Specification{
    def "receive starting point" (){
        when:

        def results = new Rover(inputCoordinates).getCoordinates()
        then:
        def expected = receivedCoordinates

        results==expected

        where:
        inputCoordinates                             || receivedCoordinates
        new Coordinates(1.0, 1.0, 'N' as char)       || new Coordinates(1.0, 1.0, 'N' as char)
        new Coordinates(2.0, 1.0, 'N' as char)       || new Coordinates(2.0, 1.0, 'N' as char)
    }

    def "throws an exception when receives unknown command"(){
        when:
        def results = new Rover().receiveCommands('u' as char)

        then:

       UnknownCommandException un = thrown()
        un.message == 'Unknown command: u'

    }
}
