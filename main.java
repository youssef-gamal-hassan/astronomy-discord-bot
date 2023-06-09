import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

public class main extends ListenerAdapter {
    public static void main(String[] args) throws InterruptedException {
        JDA jda = JDABuilder.createDefault(System.getenv("token"))
                .addEventListeners(new main())
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES)
                .build();


        jda.awaitReady();
        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(Commands.slash("say", "repeats shit").addOption(OptionType.STRING, "shit-to-repeat", "shit", true).addOption(OptionType.INTEGER, "times-to-repeat", "damn", false));
        commands.addCommands(Commands.slash("jmt", "jmt el kalb"));
        commands.addCommands(Commands.slash("lara", "wamen").addOption(OptionType.BOOLEAN, "is-stupid", "was she stupid?", true));
        commands.addCommands(Commands.slash("morad", "bitch").addOption(OptionType.BOOLEAN, "is-bird", "is he a bird", true));
        commands.queue();
    }


    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "say":
                int count = 1;
                StringBuilder content = new StringBuilder();
                if (event.getOption("times-to-repeat") != null) {
                    count = event.getOption("times-to-repeat").getAsInt();
                }
                for (int i = 0; i < count; i++) {
                    content.append(event.getOption("shit-to-repeat").getAsString()).append("\n");
                }
                event.reply(content.toString()).queue();
                break;
            case "jmt":
                event.reply("JMT el kalb").queue();
                break;
            case "lara":
                if(event.getOption("is-stupid").getAsBoolean()){
                    event.reply("Dumbass lara fuck you").queue();
                }
                else {
                    event.reply("fuck you lara").queue();
                }
                break;
            case "morad":
                if(event.getOption("is-bird").getAsBoolean()){
                    event.reply("I'm a bird").queue();
                }
                else{
                    event.reply("ya deeny ya farida").queue();
                }

                break;

                default:
                event.reply("What the fuck?!").queue();
        }
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getChannelJoined() == null){
            return;
        }
        switch(event.getMember().getId()){
            case "462753867449237506":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("JMT El Kalb Da5al el VC").queue();
                break;
            case "321367134699520000":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("ABO MOZE 7ADAR").queue();
                break;
            case "932010472268505159":
                event.getGuild().getTextChannelById("1105589927451820072").sendMessage("EWWWW LARA").queue();
                break;
            default:
                return;

        }
    }
}
